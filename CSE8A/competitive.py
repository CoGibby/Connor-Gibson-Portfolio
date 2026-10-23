import random ##using module to get a random number 
secret_number = random.randint(1,100) ##getting random number 1-100
nums_list = [str(i) for i in range(1, 101)]
guess_count = 5 
guesses = 5
print("Welcome to the Number Guessing Game!")
print("I have selected a number between 1 and 100. Can you guess it?")
competitive_mode = input("Would you like to play competitive mode (5 attempts)? (Yes/No): ")
while True:
    if competitive_mode.lower() == "yes":
        if guess_count >= 0:
            users_number = input(f"Enter your guess (or type 'exit' to quit) [{guesses} guesses left]: ")
            guess_count -= 1 
            guesses -= 1
        if users_number == "exit":
            print("Thanks for playing! The number was", secret_number)
            break
        if users_number in nums_list: 
            users_number = int(users_number)
            if users_number == secret_number:
                print("Congratulations! You guessed the number!")
                break 
            if users_number != secret_number and guesses == 0: 
                print("Sorry, you've used all your guesses. The number was", secret_number)
                break
            elif users_number < secret_number:
                print("Too low! Try again.")
            elif users_number > secret_number:
                print("Too high! Try again.")
        elif users_number not in nums_list:  
            print("Please try again, that is not a valid answer.")
    elif competitive_mode.lower() == "no":
        users_number = input("Enter your guess (or type 'exit' to quit): ")
        if users_number == "exit":
            print("Thanks for playing! The number was", secret_number)
            break
        if users_number in nums_list: 
            users_number = int(users_number)
            if users_number == secret_number:
                print("Congratulations! You guessed the number!")
                break 
            elif users_number < secret_number:
                print("Too low! Try again.")
            elif users_number > secret_number:
                print("Too high! Try again.")
        elif users_number not in nums_list:  
            print("Please try again, that is not a valid answer.")
    