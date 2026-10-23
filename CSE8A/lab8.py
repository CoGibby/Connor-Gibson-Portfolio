def print_board(board):
    """Prints the 3x3 game board."""
    for row in board:
        print(" | ".join(row))
        print("-" * 9)

def check_winner(board):
    """Checks for a winner (row, column, or diagonal)."""
    
    # Check rows and columns
    for i in range(3):
        if board[i][0] == "X" and board[i][1] == "X" and board[i][2] == "X": 
            return board[i][0]
        if board[0][i] == "X" and board[1][i] == "X" and board[2][i] == "X":
            return board[0][i]
    
    # Check diagonals
    if board[0][0] == "X" and board[1][1] == "X" and board[2][2] == "X":
        return board[0][0]
    if board[0][2] == "X" and board[1][1] == "X" and board[2][0] == "X":
        return board[0][2]
    
    return None

def is_full(board):
    """Checks if the board is full (tie game)."""
    for row in board:
        if " " not in row:
            return True
    return False


def main():
    board = [[" " for _ in range(3)] for _ in range(3)]

    while True:
        if i == 0: 
            current_player = "X"
            i += 1 
        else: 
            current_player = "O" 

        
        print_board(board)
        
        row = input(f"Player {current_player}, enter your column (0-2): ")
        row = int(row)
        col = input(f"Player {current_player}, enter your row (0-2): ")
        col = int(col)
        if board[row][col] != " ":
            print("Cell already taken. Try again.")
            continue
        
        board[col][row] = current_player
        
        winner = check_winner(board)
        if winner:
            print_board(board)
            print(f"Player {winner} wins!")
            break
        
        if is_full(board):
            print_board(board)
            print("It's a tie!")
            break
        

if __name__ == "__main__":
    main()