from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Symbol import Symbol
from machine_coding.tic_tac_toe.models.PlayerType import PlayerType


class Player:
    name: str
    symbol: Symbol
    player_type: PlayerType
    symbol: Symbol

    def __init__(self, name, symbol: Symbol):
        self.name = name
        self.symbol = symbol
        self.player_type = PlayerType.HUMAN

    def execute_move(self, board: Board) -> tuple:
        print("Please Enter row Index: ")
        row = int(input())
        print("Please Enter col Index: ")
        col = int(input())

        return row, col
