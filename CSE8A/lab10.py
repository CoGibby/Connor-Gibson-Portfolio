"""
This file contains a program that helps us plan a party for a large
number of diverse non-human critters.
"""

def get_treat(species):
    """
    Returns the kind of treat that an animal of type `species` should receive.
    """
    if species == "fish":
        return "Aqua Bites"
    elif species == "cat":
        return "Croissants"
    elif species == "dog":
        return "Cartoonishly Large Multi-Tiered Cakes"
    else:
        return "Cookies"

def get_cost(item):
    """
    Returns how much an item costs at the store.
    """
    if item == "Aqua Bites":
        return 3.20
    elif item == "Croissants":
        return 1.65
    elif item == "Cartoonishly Large Multi-Tiered Cakes":
        return 2.00
    elif item == "Cookies":
        return 2.10
    elif item == "Party hats":
        return 1.50
    else:
        # Default cost if unknown
        return 0.00

def calculate_party_cost(shopping_list):
    """
    Calculates total cost of the party given a shopping list.
    Returns the total cost as a float.
    """
    cost = 0
    for item, count in updated_list.items():
        item_cost = get_cost(item) * count
        cost += item_cost
    if item == "Party hats": 
            cost += 1.50
        
    return cost

def get_shopping_list(species_to_names):
    """
    Returns a dictionary mapping Item Name -> Quantity Needed.
    """
    shopping_list = {}
    for species, names in species_to_names.items():
        count_of_animal = len(names)
        treat = get_treat(species)
        
        # Add to existing count or initialize new count
        if treat in shopping_list:
            shopping_list[treat] += count_of_animal
        else:
            shopping_list[treat] = count_of_animal
    
    return shopping_list

def add_party_hats(shopping_list): 
    party_hats = 0 
    for treat in shopping_list: 
        if treat != "Aqua Bites": 
            party_hats += 1 
    shopping_list["Party hats"] = party_hats
    return shopping_list 

# --- MAIN PROGRAM EXECUTION ---

num_pets = int(input("How many will be attending the party? "))

# This is a dictionary mapping a species type to a list of names.
# Example: {"fish": ["Nemo", "Dory"], "dog": ["Tony", "Buddy", "Dug", "Marley"]}
species_to_names = {}

for i in range(num_pets):
    print("---" * 3)
    print(f"PET {i+1}:")
    print("---" * 3)
    name = input("Name: ")
    species = input(f"What species is {name}? ").lower()
    
    if species in species_to_names:
        species_to_names[species].append(name)
    else:
        species_to_names[species] = [name]

# 1. Get the list of food needed
shopping_list = get_shopping_list(species_to_names)

# 2. Calculate the cost
updated_list = add_party_hats(shopping_list)
cost = calculate_party_cost(updated_list)

print(f"\nIn total, the party should cost ${cost:.2f}")
