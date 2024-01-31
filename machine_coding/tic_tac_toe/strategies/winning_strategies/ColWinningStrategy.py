from typing import Dict

from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Move import Move
from machine_coding.tic_tac_toe.models.Symbol import Symbol
from machine_coding.tic_tac_toe.strategies.winning_strategies.WinningStrategy import WinningStrategy


class ColWinningStrategy(WinningStrategy):
    colMaps: Dict[int, Dict[Symbol, int]]

    def __init__(self):
        self.colMaps = {}

    def check_winner(self, board: Board, move: Move) -> bool:
        col = move.cell.col
        symbol = move.player.symbol

        if not self.colMaps.get(col):
            self.colMaps.update({col: {}})

        col_map = self.colMaps.get(col)

        if not col_map.get(symbol):
            col_map.update({symbol: 0})

        col_map.update({symbol: col_map.get(symbol) + 1})

        return col_map.get(symbol) == board.size

    def handle_undo(self, board: Board, move: Move):
        col = move.cell.col
        symbol = move.player.symbol

        col_map = self.colMaps.get(col)
        col_map.update({symbol: col_map.get(symbol) - 1})
