from typing import Dict

from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Move import Move

from machine_coding.tic_tac_toe.models.Symbol import Symbol
from machine_coding.tic_tac_toe.strategies.winning_strategies.WinningStrategy import WinningStrategy


class DiagonalWinningStrategy(WinningStrategy):
    leftDiagonal: Dict[Symbol, int]
    rightDiagonal: Dict[Symbol, int]

    def __init__(self):
        self.leftDiagonal = {}
        self.rightDiagonal = {}

    def check_winner(self, board: Board, move: Move) -> bool:

        row = move.cell.row
        col = move.cell.col
        symbol = move.player.symbol

        if row == col:
            if not self.leftDiagonal.get(symbol):
                self.leftDiagonal.update({symbol: 1})
            self.leftDiagonal.update({symbol: self.leftDiagonal.get(symbol) + 1})
            return self.leftDiagonal.get(symbol) == board.size

        if (row + col) == board.size - 1:
            if not self.rightDiagonal.get(symbol):
                self.rightDiagonal.update({symbol: 1})
            self.rightDiagonal.update({symbol: self.rightDiagonal.get(symbol) + 1})
            return self.rightDiagonal.get(symbol) == board.size

        return False

    def handle_undo(self, board: Board, move: Move):
        row = move.cell.row
        col = move.cell.col
        symbol = move.player.symbol

        if row == col:
            self.leftDiagonal.update({symbol, self.leftDiagonal.get(symbol) - 1})

        if (row + col) == board.size-1:
            self.rightDiagonal.update({symbol, self.rightDiagonal.get(symbol) - 1})
