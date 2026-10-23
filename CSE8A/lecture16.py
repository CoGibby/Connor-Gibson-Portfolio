def calculate_base_grade(homework_points, exam_points):
    """
    Determine base letter grade based on homework and exam points.
    Args:
        homework_points: Total homework points (max 18)
        exam_points: Total exam points (max 6)
    Returns:
        str: Base letter grade ('A', 'B', 'C', or 'F')
    Grading criteria:
    - A: At least 15 homework points AND 6 exam points
    - B: At least 12 homework points AND 4 exam points
    - C: At least 9 homework points AND 2 exam points
    - F: Does not meet requirements for C
    """
    base_grade = None
    if homework_points >= 15 and exam_points >= 6:
        base_grade = 'A'
    elif homework_points >= 12 and exam_points >= 4:
        base_grade = 'B'
    elif homework_points >= 9 and exam_points >= 2:
        base_grade = 'C'
    else:
        base_grade = 'F'
    return base_grade
def calculate_social_modifier(social_points):
    """
    Determine social learning modifier based on total social points.
    Args:
        social_points: Total social learning points (max 30)
    Returns:
        str: Modifier ('+', '', '-', or 'lower')
    Modifier criteria:
    - '+': At least 24 total social points
    - '': 18 to 23 total social points
    - '-': 12 to 17 total social points
    - 'lower': Fewer than 12 total social points
    """
    if social_points >= 24:
        return '+'
    elif 18 <= social_points <= 23:
        return ''    # No modifier
    elif 12 <= social_points <= 17:
        return '-'
    else: 
        return 'lower'

def calculate_final_grade(homework_points, exam_points, social_points):
    """
    Calculate the final letter grade including social learning modifier.
    Args:
        homework_points: Total homework points (max 18)
        exam_points: Total exam points (max 6)
        social_points: Total social learning points (max 30)
    Returns:
        str: Final letter grade with modifier
    """
    base_grade = calculate_base_grade(homework_points, exam_points)
    social_modifier = calculate_social_modifier(social_points)
    final_grade = base_grade + social_modifier
    return final_grade


# Prompt user for input for their total homework, exam, 
# and social learning points
homework_points = int(input("Enter total homework points (max 18): "))
exam_points = int(input("Enter total exam points (max 6): "))
social_points = int(input("Enter total social learning points (max 30): "))


# Calculate and display the final letter grade
final_grade = calculate_final_grade(homework_points, exam_points, social_points)
print(f"Your final letter grade is: {final_grade}")
