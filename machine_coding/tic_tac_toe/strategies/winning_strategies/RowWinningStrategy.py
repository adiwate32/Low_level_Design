from typing import Dict

from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Move import Move
from machine_coding.tic_tac_toe.models.Symbol import Symbol

from machine_coding.tic_tac_toe.strategies.winning_strategies.WinningStrategy import WinningStrategy


class RowWinningStrategy(WinningStrategy):
    rowMaps: Dict[int, Dict[Symbol, int]]

    def __init__(self):
        self.rowMaps = {}

    def check_winner(self, board: Board, move: Move) -> bool:
        row = move.cell.row
        symbol = move.player.symbol
        if not self.rowMaps.get(row):
            self.rowMaps.update({row: {}})
        row_map = self.rowMaps.get(row)

        if not row_map.get(symbol):
            row_map.update({symbol: 0})

        row_map.update({symbol: row_map.get(symbol) + 1})
        return row_map.get(symbol) == board.size

    def handle_undo(self, board: Board, move: Move):
        row = move.cell.row
        symbol = move.player.symbol

        row_map = self.rowMaps.get(row)
        row_map.update({symbol: row_map.get(symbol) - 1})
