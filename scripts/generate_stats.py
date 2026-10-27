import os
import re
import shutil

# Target problem thresholds for the visual grid
TARGETS = {
    "Easy": 200,
    "Medium": 400,
    "Hard": 150
}

# File extensions map to track languages used
LANGUAGE_EXTENSIONS = {
    ".py": "Python",
    ".java": "Java",
    ".cpp": "C++",
    ".cc": "C++",
    ".js": "JavaScript",
    ".ts": "TypeScript",
    ".c": "C",
    ".go": "Go",
    ".rs": "Rust"
}

def clean_and_organize_folders():
    """Scans the root directory for loose problem folders and moves them based on their internal README metadata."""
    for item in os.listdir("."):
        if os.path.isdir(item) and item not in [".git", ".github", "scripts", "Easy", "Medium", "Hard"]:
            readme_path = os.path.join(item, "README.md")
            if os.path.exists(readme_path):
                with open(readme_path, "r", encoding="utf-8") as f:
                    readme_text = f.read()
                
                difficulty = None
                if "Difficulty: Easy" in readme_text:
                    difficulty = "Easy"
                elif "Difficulty: Medium" in readme_text:
                    difficulty = "Medium"
                elif "Difficulty: Hard" in readme_text:
                    difficulty = "Hard"
                
                if difficulty:
                    os.makedirs(difficulty, exist_ok=True)
                    target_path = os.path.join(difficulty, item)
                    if os.path.exists(target_path):
                        shutil.rmtree(target_path)
                    shutil.move(item, difficulty)

def count_problems(difficulty_dir):
    if not os.path.exists(difficulty_dir):
        return 0
    return len([d for d in os.listdir(difficulty_dir) if os.path.isdir(os.path.join(difficulty_dir, d))])

def scan_languages():
    """Scans the Easy, Medium, and Hard folders to see what programming languages were used."""
    lang_counts = {}
    for difficulty in ["Easy", "Medium", "Hard"]:
        if os.path.exists(difficulty):
            for root, dirs, files in os.walk(difficulty):
                for file in files:
                    ext = os.path.splitext(file)[1].lower()
                    if ext in LANGUAGE_EXTENSIONS:
                        lang_name = LANGUAGE_EXTENSIONS[ext]
                        lang_counts[lang_name] = lang_counts.get(lang_name, 0) + 1
    return lang_counts

def generate_blocks(solved, total, color_emoji):
    max_blocks = 30
    percentage = solved / total if total > 0 else 0
    filled = min(max_blocks, int(percentage * max_blocks))
    return color_emoji * filled + "⬜" * (max_blocks - filled)

def update_readme():
    # 1. Run the organizer first
    clean_and_organize_folders()
    
    # 2. Gather metrics
    easy_count = count_problems("Easy")
    medium_count = count_problems("Medium")
    hard_count = count_problems("Hard")
    total_solved = easy_count + medium_count + hard_count
    
    languages = scan_languages()

    # 3. Format Language Section Strings
    lang_section = ""
    if languages:
        lang_section = "\n### 🛠️ Language Breakdown\n"
        for lang, count in sorted(languages.items(), key=lambda x: x[1], reverse=True):
            lang_section += f"- **{lang}**: {count} problems solved\n"

    # 4. Generate Dashboard with colors
    dashboard = f"""
# LeetCode Practice Dashboard

{total_solved} solved · {easy_count} easy · {medium_count} medium · {hard_count} hard

**Easy Problems** ({easy_count}/{TARGETS['Easy']})
{generate_blocks(easy_count, TARGETS['Easy'], "🟩")}

**Medium Problems** ({medium_count}/{TARGETS['Medium']})
{generate_blocks(medium_count, TARGETS['Medium'], "🟨")}

**Hard Problems** ({hard_count}/{TARGETS['Hard']})
{generate_blocks(hard_count, TARGETS['Hard'], "🟥")}
{lang_section}"""

    readme_path = "README.md"
    content = ""
    if os.path.exists(readme_path):
        with open(readme_path, "r", encoding="utf-8") as f:
            content = f.read()

    if "<!-- START_DASHBOARD -->" in content:
        pattern = r"<!-- START_DASHBOARD -->.*?<!-- END_DASHBOARD -->"
        replacement = f"<!-- START_DASHBOARD -->\n{dashboard}\n<!-- END_DASHBOARD -->"
        new_content = re.sub(pattern, replacement, content, flags=re.DOTALL)
    else:
        new_content = content + f"\n<!-- START_DASHBOARD -->\n{dashboard}\n<!-- END_DASHBOARD -->"

    with open(readme_path, "w", encoding="utf-8") as f:
        f.write(new_content)

if __name__ == "__main__":
    update_readme()
