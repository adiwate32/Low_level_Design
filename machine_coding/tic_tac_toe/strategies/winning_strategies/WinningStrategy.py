import abc
from abc import ABC

from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Move import Move


class WinningStrategy(ABC):

    @abc.abstractmethod
    def check_winner(self, board: Board, move: Move) -> bool:
        pass

    @abc.abstractmethod
    def handle_undo(self, board: Board, move: Move):
        pass
