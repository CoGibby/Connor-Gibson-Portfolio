def calculate_base_grade(homework_points, exam_points):
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
    if social_points >= 24:
        return '+'
    elif 18 <= social_points <= 23:
        return ''    # No modifier
    elif 12 <= social_points <= 17:
        return '-'
    elif social_points < 12 and final_grade != "F": 
        return 'lower'
    

def calculate_final_grade(homework_points, exam_points, social_points):
    base_grade = calculate_base_grade(homework_points, exam_points)
    social_modifier = calculate_social_modifier(social_points)
    final_grade = base_grade + social_modifier
    if social_modifier == 'lower' and base_grade == 'A':
        final_grade = 'B'
    elif social_modifier == 'lower' and base_grade == 'B': 
        final_grade = 'C'
    elif social_modifier == 'lower' and base_grade == 'C':
        final_grade = 'F'
    elif social_modifier == 'lower' and base_grade == 'F':
        final_grade = 'F'
    return final_grade

# Prompt user for input for their total homework, exam, and social learning points
homework_points = int(input("Enter total homework points (max 18): "))
exam_points = int(input("Enter total exam points (max 6): "))
social_points = int(input("Enter total social learning points (max 30): "))

final_grade = calculate_final_grade(homework_points, exam_points, social_points)
print(f"Your final letter grade is: {final_grade}")
