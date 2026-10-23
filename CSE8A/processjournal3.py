def string_to_list(words_str):
    empty_list = words_str.split(" ")
    return empty_list

def word_occurence_dict(lyrics):
    empty_dict = {} 
    empty_list2 = lyrics.split(" ")
    
    for index in range(len(empty_list2)):
        words = empty_list2[index]
        if words in empty_dict:
            empty_dict[words].append(index)
        else:  
            empty_dict[words] = [index]
    return empty_dict
    

test1 = "let it be let it be let it be"
test2 = "yesterday all my troubles seemed so far away"
test3 = "in an octopus jungle in the shade"
sample1 = "you're giving me a million reasons to let you go"
sample2 = "200 people love to be around the SWAMP"
sample3 = "Prud is a very good Tutor and I'm going to give him a big hug"
print(word_occurence_dict(sample1))
print(word_occurence_dict(sample2))
print(word_occurence_dict(sample3))
print(string_to_list(test1))
print(string_to_list(test2))
print(string_to_list(test3))
