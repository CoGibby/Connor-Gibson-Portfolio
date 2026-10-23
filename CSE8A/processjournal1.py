def social_learning_grade(reading_quiz,lectures,lab,study_group):
   total_points = 0
   if reading_quiz == 1 and lectures >= 1 and lab == 1 and study_group == 1:
      social_points = 3 
   elif reading_quiz == 1 and lectures == 2 and lab == 1 and study_group == 0: 
      social_points = 3 
   elif reading_quiz == 1 and lectures == 0 and lab == 1 and study_group == 1: 
      social_points = 2
   elif reading_quiz == 0 and lectures == 1 and lab == 1 and study_group == 1:
      social_points = 2 
   elif study_group == 1 and lectures >= 1 and reading_quiz == 1 or lab == 1:
      social_points = 2 
   elif study_group + lectures + reading_quiz + lab >= 2:
      social_points = 1 
   else :
      social_points = 0 
   return social_points 
print(social_learning_grade(1,1,1,0)) ## Expected Output: 2 
print(social_learning_grade(1,0,0,0)) ##Expected output: 0 
print(social_learning_grade(1,2,1,0)) ##Expected output: 3