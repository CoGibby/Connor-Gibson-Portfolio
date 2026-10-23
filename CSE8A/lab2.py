# --- Lab Task Starting Code: CSE 8A-6R Grading System ---
# Goal: Refactor the repeated, complex logic below into a single function.

# --- Grading Thresholds (from our syllabus) ---
# A: HW >= 15 AND Exam >= 6
# B: HW >= 12 AND Exam >= 4
# C: HW >= 9 AND Exam >= 2
# F: Thresholds for C not met

# --- Student 1: Aisha (Expected Grade: A) ---
aisha_name = "Aisha"
aisha_hw_total = 18  # Max 18 points total
aisha_exam_total = 6 # Max 6 points total
aisha_grade = None

def calculate_grade(hw_total, exam_total):
    # Conditional logic goes here
    if hw_total >= 15 and exam_total >= 6:
        return "A"
    elif hw_total >= 12 and exam_total >= 4:
        return "B" 
    elif hw_total >= 9 and exam_total >= 2:
        return "C"
    else:
        return "F"
    
def print_student_grade(name, hw_total, exam_total):
    # 1. Call the first function to get the grade
    grade = calculate_grade(hw_total, exam_total)
    
    # 2. Print the final output
    print(f"--- {aisha_name}---")
    print(f"--- {aisha_grade}---")
    print(f"--- {aisha_hw_total}---", {aisha_exam_total})

print(f"--- {aisha_name} ---")
print(f"HW Total: {aisha_hw_total}, Exam Total: {aisha_exam_total}")
print(f"Final Grade: {aisha_grade}")
print("-" * 30)

# --- Student 2: Kenji (Expected Grade: B) ---
kenji_name = "Kenji"
kenji_hw_total = 14
kenji_exam_total = 4
kenji_grade = None

if kenji_hw_total >= 15 and kenji_exam_total >= 6:
    kenji_grade = "A"
elif kenji_hw_total >= 12 and kenji_exam_total >= 4:
    kenji_grade = "B"
elif kenji_hw_total >= 9 and kenji_exam_total >= 2:
    kenji_grade = "C"
else:
    kenji_grade = "F"

print(f"--- {kenji_name} ---")
print(f"HW Total: {kenji_hw_total}, Exam Total: {kenji_exam_total}")
print(f"Final Grade: {kenji_grade}")
print("-" * 30)

# --- Student 3: Priya (Expected Grade: C) ---
priya_name = "Priya"
priya_hw_total = 10
priya_exam_total = 2
priya_grade = None

if priya_hw_total >= 15 and priya_exam_total >= 6:
    priya_grade = "A"
elif priya_hw_total >= 12 and priya_exam_total >= 4:
    priya_grade = "B"
elif priya_hw_total >= 9 and priya_exam_total >= 2:
    priya_grade = "C"
else:
    priya_grade = "F"

print(f"--- {priya_name} ---")
print(f"HW Total: {priya_hw_total}, Exam Total: {priya_exam_total}")
print(f"Final Grade: {priya_grade}")
print("-" * 30)