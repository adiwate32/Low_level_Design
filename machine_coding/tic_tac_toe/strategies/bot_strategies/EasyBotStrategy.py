from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.CellState import CellState
from machine_coding.tic_tac_toe.models.Move import Move
from machine_coding.tic_tac_toe.strategies.bot_strategies.BotStrategy import BotStrategy


class EasyBotStrategy(BotStrategy):
    def bot_move(self, board: Board) -> tuple:
        size = board.size

        for i in range(size):
            for j in range(size):
                cell = board.board[i][j]

                if cell.cell_state == CellState.EMPTY:
                    return i, j
