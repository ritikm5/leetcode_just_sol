import os
import re

# Total standard baseline targets for standard practice trackers
TARGETS = {
    "Easy": 200,
    "Medium": 400,
    "Hard": 150
}

def count_problems(difficulty_dir):
    if not os.path.exists(difficulty_dir):
        return 0
    # Counts individual problem subdirectories pushed by LeetCommit
    return len([d for d in os.listdir(difficulty_dir) if os.path.isdir(os.path.join(difficulty_dir, d))])

def generate_blocks(solved, total):
    max_blocks = 30
    percentage = solved / total if total > 0 else 0
    filled = min(max_blocks, int(percentage * max_blocks))
    return "🟩" * filled + "⬜" * (max_blocks - filled)

def update_readme():
    easy_count = count_problems("Easy")
    medium_count = count_problems("Medium")
    hard_count = count_problems("Hard")
    total_solved = easy_count + medium_count + hard_count

    # Generate Markdown Dashboard Segment
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

    # Read existing content and append or wrap around tag markers
    readme_path = "README.md"
    content = ""
    if os.path.exists(readme_path):
        with open(readme_path, "r", encoding="utf-8") as f:
            content = f.read()

    # Split or insert between tags to preserve personal introductions
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
