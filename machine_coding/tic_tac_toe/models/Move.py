from machine_coding.tic_tac_toe.models.Cell import Cell
from machine_coding.tic_tac_toe.models.Player import Player


class Move:
    player: Player
    cell: Cell

    def __init__(self, cell: Cell, player: Player):
        self.cell = cell
        self.player = player
