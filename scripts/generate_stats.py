
import os
import re
import shutil

# Target problem thresholds for the visual grid
TARGETS = {
    "Easy": 200,
    "Medium": 400,
    "Hard": 150,
}

# File extensions mapped to programming languages
LANGUAGE_EXTENSIONS = {
    ".py": "Python",
    ".java": "Java",
    ".cpp": "C++",
    ".cc": "C++",
    ".js": "JavaScript",
    ".ts": "TypeScript",
    ".c": "C",
    ".go": "Go",
    ".rs": "Rust",
}

DIFFICULTIES = ("Easy", "Medium", "Hard")
EXCLUDED_ROOT_DIRS = {
    ".git",
    ".github",
    "scripts",
    "Easy",
    "Medium",
    "Hard",
}


def clean_and_organize_folders():
    """Move loose problem folders into difficulty folders without deleting data."""
    for item in os.listdir("."):
        if item in EXCLUDED_ROOT_DIRS:
            continue

        if not os.path.isdir(item):
            continue

        readme_path = os.path.join(item, "README.md")

        if not os.path.isfile(readme_path):
            continue

        try:
            with open(readme_path, "r", encoding="utf-8") as file:
                readme_text = file.read().lower()

            # Check for difficulty labels without matching words like "uneasy".
            difficulty = None

            for level in DIFFICULTIES:
                if re.search(rf"\b{level.lower()}\b", readme_text):
                    difficulty = level
                    break

            if difficulty is None:
                continue

            os.makedirs(difficulty, exist_ok=True)
            target_path = os.path.join(difficulty, item)

            # Never overwrite or delete an existing problem folder.
            if os.path.exists(target_path):
                print(
                    f"Skipping {item}: destination already exists at "
                    f"{target_path}"
                )
                continue

            shutil.move(item, target_path)
            print(f"Moved {item} -> {target_path}")

        except (OSError, UnicodeError) as error:
            print(f"Skipping folder {item} due to: {error}")


def count_problems(difficulty_dir):
    """Count problem subdirectories inside a difficulty directory."""
    if not os.path.isdir(difficulty_dir):
        return 0

    return sum(
        1
        for entry in os.scandir(difficulty_dir)
        if entry.is_dir()
    )


def scan_languages():
    """Count source-code files by programming language."""
    lang_counts = {}

    for difficulty in DIFFICULTIES:
        if not os.path.isdir(difficulty):
            continue

        for root, _, files in os.walk(difficulty):
            for filename in files:
                # splitext returns (filename_without_extension, extension).
                extension = os.path.splitext(filename)[1].lower()

                if extension in LANGUAGE_EXTENSIONS:
                    language = LANGUAGE_EXTENSIONS[extension]
                    lang_counts[language] = (
                        lang_counts.get(language, 0) + 1
                    )

    return lang_counts


def generate_blocks(solved, total, color_emoji):
    """Generate a progress bar capped at 30 blocks."""
    max_blocks = 30
    percentage = solved / total if total > 0 else 0
    filled = min(max_blocks, int(percentage * max_blocks))

    return color_emoji * filled + "⬜" * (max_blocks - filled)


def update_readme():
    """Organize problem folders and update the README dashboard."""
    clean_and_organize_folders()

    # Gather metrics
    easy_count = count_problems("Easy")
    medium_count = count_problems("Medium")
    hard_count = count_problems("Hard")
    total_solved = easy_count + medium_count + hard_count

    languages = scan_languages()

    # Build language breakdown
    lang_section = ""

    if languages:
        lang_section = "\n### 🛠️ Language Breakdown\n"

        for language, count in sorted(
            languages.items(),
            key=lambda item: (-item[1], item[0]),
        ):
            lang_section += f"- **{language}**: {count} source files\n"

    # Build dashboard
    dashboard = f"""## 📊 LeetCode Practice Dashboard

**Total Solved:** {total_solved} | 🟩 {easy_count} Easy | 🟨 {medium_count} Medium | 🟥 {hard_count} Hard

---

**Easy Problems** ({easy_count}/{TARGETS['Easy']})
{generate_blocks(easy_count, TARGETS['Easy'], "🟩")}

**Medium Problems** ({medium_count}/{TARGETS['Medium']})
{generate_blocks(medium_count, TARGETS['Medium'], "🟨")}

**Hard Problems** ({hard_count}/{TARGETS['Hard']})
{generate_blocks(hard_count, TARGETS['Hard'], "🟥")}
{lang_section}"""

    readme_path = "README.md"

    if os.path.isfile(readme_path):
        with open(readme_path, "r", encoding="utf-8") as file:
            content = file.read()
    else:
        content = "# LeetCode Solutions\n"

    start_marker = "<!-- START_DASHBOARD -->"
    end_marker = "<!-- END_DASHBOARD -->"

    if start_marker in content and end_marker in content:
        pattern = re.escape(start_marker) + r".*?" + re.escape(end_marker)

        replacement = (
            f"{start_marker}\n{dashboard}\n{end_marker}"
        )

        new_content, replacements = re.subn(
            pattern,
            lambda _: replacement,
            content,
            count=1,
            flags=re.DOTALL,
        )

        if replacements == 0:
            print("Warning: dashboard markers were not updated.")
            return
    elif start_marker in content or end_marker in content:
        print(
            "Error: README.md has only one dashboard marker. "
            "Fix the markers before updating the dashboard."
        )
        return
    else:
        new_content = (
            content.rstrip()
            + f"\n\n{start_marker}\n{dashboard}\n{end_marker}\n"
        )

    with open(readme_path, "w", encoding="utf-8") as file:
        file.write(new_content)

    print("Dashboard updated successfully.")
    print(
        f"Total: {total_solved} | Easy: {easy_count} | "
        f"Medium: {medium_count} | Hard: {hard_count}"
    )


if __name__ == "__main__":
    update_readme()
