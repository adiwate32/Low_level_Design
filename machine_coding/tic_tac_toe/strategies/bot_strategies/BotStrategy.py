from abc import ABC, abstractmethod

from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Move import Move


class BotStrategy(ABC):

    @abstractmethod
    def bot_move(self, board: Board):
        pass
