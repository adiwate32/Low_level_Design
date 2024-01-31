from machine_coding.tic_tac_toe.models.CellState import CellState
from machine_coding.tic_tac_toe.models.Symbol import Symbol


class Cell:
    row: int
    col: int
    symbol: Symbol
    cell_state: CellState

    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.symbol = None
        self.cell_state = CellState.EMPTY
