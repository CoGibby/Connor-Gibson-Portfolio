dining_dolls = 6700
school_days = 185 
average = dining_dolls / school_days 
cash = float(input("How much dining dollars do you have?"))
days = float(input("What day are you on?")) 
days_left = school_days - days 
cash_spent = dining_dolls - cash
your_average = cash_spent / days
new_average = cash / days_left 
if days > 0 and cash < 6300:
    print("The original average was", average, ", but now, you're average is", your_average)
if your_average < average:
    print("You are saving money!")
else: 
    print("You are not budgeting properly")
print("You have spent: ", cash_spent, "dollars.")
print("From now on, you should try to spend ", new_average, "dollars a day.")
    