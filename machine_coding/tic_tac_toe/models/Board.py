from typing import List

from machine_coding.tic_tac_toe.models.Cell import Cell
from machine_coding.tic_tac_toe.models.CellState import CellState


class Board:
    size: int
    board: List[List[Cell]]

    def __init__(self, size):
        self.size = size
        self.board = [[None] * size for _ in range(size)]

        for i in range(size):
            for j in range(size):
                self.board[i][j] = Cell(i, j)

    def display_board(self):
        for i in range(self.size):
            for j in range(self.size):
                cell = self.board[i][j]
                if cell.cell_state == CellState.EMPTY:
                    print("| |", end="")
                else:
                    print(f"|{cell.symbol.char}|", end="")
            print("\n")
