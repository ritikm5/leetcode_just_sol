import os
import re
import shutil

# Target problem thresholds for the visual grid
TARGETS = {
    "Easy": 200,
    "Medium": 400,
    "Hard": 150
}

def clean_and_organize_folders():
    """Scans the root directory for loose problem folders and moves them based on their internal README metadata."""
    for item in os.listdir("."):
        # Look for problem folders (ignoring system folders, scripts, and difficulty folders)
        if os.path.isdir(item) and item not in [".git", ".github", "scripts", "Easy", "Medium", "Hard"]:
            readme_path = os.path.join(item, "README.md")
            if os.path.exists(readme_path):
                with open(readme_path, "r", encoding="utf-8") as f:
                    readme_text = f.read()
                
                # Check LeetCode metadata tags for difficulty
                difficulty = None
                if "Difficulty: Easy" in readme_text:
                    difficulty = "Easy"
                elif "Difficulty: Medium" in readme_text:
                    difficulty = "Medium"
                elif "Difficulty: Hard" in readme_text:
                    difficulty = "Hard"
                
                if difficulty:
                    # Create the folder (Easy/Medium/Hard) if it doesn't exist yet
                    os.makedirs(difficulty, exist_ok=True)
                    target_path = os.path.join(difficulty, item)
                    
                    # If it's already there, remove old one to prevent errors, then move
                    if os.path.exists(target_path):
                        shutil.rmtree(target_path)
                    shutil.move(item, difficulty)
                    print(f"Moved {item} to {difficulty}/")

def count_problems(difficulty_dir):
    if not os.path.exists(difficulty_dir):
        return 0
    return len([d for d in os.listdir(difficulty_dir) if os.path.isdir(os.path.join(difficulty_dir, d))])

def generate_blocks(solved, total):
    max_blocks = 30
    percentage = solved / total if total > 0 else 0
    filled = min(max_blocks, int(percentage * max_blocks))
    return "🟩" * filled + "⬜" * (max_blocks - filled)

def update_readme():
    # 1. Run the organizer first!
    clean_and_organize_folders()
    
    # 2. Count the newly organized folders
    easy_count = count_problems("Easy")
    medium_count = count_problems("Medium")
    hard_count = count_problems("Hard")
    total_solved = easy_count + medium_count + hard_count

    # 3. Generate Markdown Dashboard Segment
    dashboard = f"""
# LeetCode Practice Dashboard

{total_solved} solved · {easy_count} easy · {medium_count} medium · {hard_count} hard

**Easy Problems** ({easy_count}/{TARGETS['Easy']})
{generate_blocks(easy_count, TARGETS['Easy'])}

**Medium Problems** ({medium_count}/{TARGETS['Medium']})
{generate_blocks(medium_count, TARGETS['Medium'])}

**Hard Problems** ({hard_count}/{TARGETS['Hard']})
{generate_blocks(hard_count, TARGETS['Hard'])}
"""

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
