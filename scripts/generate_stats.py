
import os
import re
import shutil

TARGETS = {
    "easy": 200,
    "medium": 400,
    "hard": 150,
}

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

ROOT_EXCLUSIONS = {
    ".git",
    ".github",
    ".venv",
    "venv",
    "node_modules",
    "scripts",
    "solutions",
    "easy",
    "medium",
    "hard",
    "__pycache__",
}


def detect_difficulty(readme_path):
    """Detect a problem's difficulty from its README."""
    if not os.path.isfile(readme_path):
        return None

    try:
        with open(readme_path, "r", encoding="utf-8") as file:
            content = file.read()
    except (OSError, UnicodeError) as error:
        print(f"Could not read {readme_path}: {error}")
        return None

    # Prefer an explicit difficulty label.
    match = re.search(
        r"difficulty\s*[:\-]\s*(easy|medium|hard)\b",
        content,
        re.IGNORECASE,
    )

    if match:
        return match.group(1).lower()

    # Support READMEs with headings such as "## Medium".
    match = re.search(
        r"^\s{0,3}#{1,6}\s*(easy|medium|hard)\s*$",
        content,
        re.IGNORECASE | re.MULTILINE,
    )

    if match:
        return match.group(1).lower()

    # Do not guess when the README doesn't clearly identify difficulty.
    return None


def organize_solutions():
    """Move identifiable root-level problem folders into solutions/<difficulty>."""
    os.makedirs("solutions", exist_ok=True)

    for difficulty in TARGETS:
        os.makedirs(os.path.join("solutions", difficulty), exist_ok=True)

    for item in os.listdir("."):
        if item.lower() in ROOT_EXCLUSIONS:
            continue

        if not os.path.isdir(item):
            continue

        readme_path = os.path.join(item, "README.md")
        difficulty = detect_difficulty(readme_path)

        if difficulty is None:
            continue

        destination = os.path.join(
            "solutions",
            difficulty,
            item,
        )

        # Never overwrite an existing problem folder.
        if os.path.exists(destination):
            print(f"Skipping duplicate; preserving both folders: {item}")
            continue

        try:
            shutil.move(item, destination)
            print(f"Organized: {item} -> {destination}")
        except OSError as error:
            print(f"Could not move {item}: {error}")


def count_problems(difficulty):
    """Count problem directories inside solutions/<difficulty>."""
    directory = os.path.join("solutions", difficulty)

    if not os.path.isdir(directory):
        return 0

    return sum(
        1
        for entry in os.scandir(directory)
        if entry.is_dir()
    )


def scan_languages():
    """Count supported source files under solutions/."""
    counts = {}

    for difficulty in TARGETS:
        directory = os.path.join("solutions", difficulty)

        if not os.path.isdir(directory):
            continue

        for root, _, files in os.walk(directory):
            for filename in files:
                extension = os.path.splitext(filename)[1].lower()

                if extension in LANGUAGE_EXTENSIONS:
                    language = LANGUAGE_EXTENSIONS[extension]
                    counts[language] = counts.get(language, 0) + 1

    return counts


def generate_blocks(solved, target, emoji):
    """Build a 30-block progress bar."""
    total_blocks = 30
    filled = min(total_blocks, int(solved / target * total_blocks))
    return emoji * filled + "⬜" * (total_blocks - filled)


def update_readme():
    organize_solutions()

    easy = count_problems("easy")
    medium = count_problems("medium")
    hard = count_problems("hard")
    total = easy + medium + hard

    languages = scan_languages()

    language_section = ""
    if languages:
        language_section = "\n### 🛠️ Language Breakdown\n"
        for language, count in sorted(
            languages.items(),
            key=lambda item: (-item[1], item[0]),
        ):
            language_section += (
                f"- **{language}**: {count} source files\n"
            )

    dashboard = f"""## 📊 LeetCode Practice Dashboard

**Total Solved:** {total} | 🟩 {easy} Easy | 🟨 {medium} Medium | 🟥 {hard} Hard

---

**Easy Problems** ({easy}/{TARGETS["easy"]})
{generate_blocks(easy, TARGETS["easy"], "🟩")}

**Medium Problems** ({medium}/{TARGETS["medium"]})
{generate_blocks(medium, TARGETS["medium"], "🟨")}

**Hard Problems** ({hard}/{TARGETS["hard"]})
{generate_blocks(hard, TARGETS["hard"], "🟥")}
{language_section}"""

    readme_path = "README.md"
    start = "<!-- START_DASHBOARD -->"
    end = "<!-- END_DASHBOARD -->"

    if os.path.isfile(readme_path):
        with open(readme_path, "r", encoding="utf-8") as file:
            content = file.read()
    else:
        content = "# LeetCode\n"

    if (start in content) != (end in content):
        raise ValueError(
            "README.md must contain both dashboard markers, "
            "or neither marker."
        )

    replacement = f"{start}\n{dashboard}\n{end}"

    if start in content:
        pattern = re.escape(start) + r".*?" + re.escape(end)
        content, replacements = re.subn(
            pattern,
            lambda _: replacement,
            content,
            count=1,
            flags=re.DOTALL,
        )

        if replacements != 1:
            raise ValueError("Could not update the README dashboard.")
    else:
        content = content.rstrip() + "\n\n" + replacement + "\n"

    with open(readme_path, "w", encoding="utf-8") as file:
        file.write(content)

    print("\nDashboard updated successfully!")
    print(f"Total: {total} | Easy: {easy} | Medium: {medium} | Hard: {hard}")


if __name__ == "__main__":
    update_readme()
