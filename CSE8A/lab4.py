import random ##using module to get a random number 
secret_number = random.randint(1,100) ##getting random number 1-100
guess_count = 0 
print("Welcome to the Number Guessing Game!")
print("I have selected a number between 1 and 100. Can you guess it?")
competitive_mode = input("Would you like to play competitive mode? (Yes/No): ")
while True:
    if competitive_mode == "Yes":
        if guess_count == 0:
            users_number = input("Enter your guess (or type 'exit' to quit) [3 guesses left]: ")
            guess_count += 1 
        elif guess_count == 1:
            users_number = input("Enter your guess (or type 'exit' to quit) [2 guesses left]: ")
            guess_count += 1
        elif guess_count == 2:
            users_number = input("Enter your guess (or type 'exit' to quit) [1 guesses left]: ")
            guess_count += 1
        if users_number != secret_number and guess_count == 3: 
            print("Sorry, you've used all your guesses. The number was", secret_number)
            break
        if users_number == "exit":
            print("Thanks for playing! The number was", secret_number)
            break
        if users_number != int: 
            print("That is not a valid number.") 
        if users_number == secret_number:
            print("Congratulations! You guessed the number!")
            break 
        elif users_number < secret_number: 
            print("Too low! Try again.")
        elif users_number > secret_number:
            print("Too high! Try again.")
    elif competitive_mode == "No":
        users_number = print("Enter your guess (or type 'exit' to quit): ")
    else: 
        print("That is not a valid answer. ", competitive_mode)
    if users_number == "exit":
        print("Thanks for playing! The number was", secret_number)
        break 
    if users_number == secret_number:
        print("Congratulations! You guessed the number!")
        break 
    elif users_number < secret_number: 
        print("Too low! Try again.")
    elif users_number > secret_number:
        print("Too high! Try again.")