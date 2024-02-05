from typing import List


from machine_coding.tic_tac_toe.exceptions.InvalidBotCountException import (
    InvalidBotCountException,
)
from machine_coding.tic_tac_toe.exceptions.InvalidMoveException import (
    InvalidMoveException,
)
from machine_coding.tic_tac_toe.exceptions.InvalidPlayersCountException import (
    InvalidPlayersCountException,
)
from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.Cell import Cell
from machine_coding.tic_tac_toe.models.CellState import CellState
from machine_coding.tic_tac_toe.models.GameState import GameState
from machine_coding.tic_tac_toe.models.Move import Move
from machine_coding.tic_tac_toe.models.MoveType import MoveType
from machine_coding.tic_tac_toe.models.Player import Player
from machine_coding.tic_tac_toe.models.PlayerType import PlayerType
from machine_coding.tic_tac_toe.strategies.winning_strategies.WinningStrategy import (
    WinningStrategy,
)


class Builder:
    players_list: List[Player]
    winning_strategies: List[WinningStrategy]
    dimension: int

    def __init__(self):
        self.players_list = []
        self.winning_strategies = []
        self.dimension = 0

    def set_players(self, players):
        self.players_list = players
        return self

    def set_winning_strategies(self, strategies):
        self.winning_strategies = strategies
        return self

    def set_dimension(self, dimension):
        self.dimension = dimension
        return self

    def validate_bot_count(self) -> int:
        cnt = 0

        for player in self.players_list:
            if player.player_type == PlayerType.BOT:
                cnt += 1

        return cnt

    def validate(self):
        if self.validate_bot_count() > 1:
            raise InvalidBotCountException("No of bots should be equal to 1")

        if len(self.players_list) != (self.dimension - 1):
            raise InvalidPlayersCountException(
                "No of players should be equal to board size - 1"
            )

    def build(self):
        self.validate()

        return Game(self.players_list, self.dimension, self.winning_strategies)


class Game:
    players_list: List[Player]
    board: Board
    moves_list: List[Move]
    next_player_index: int
    game_state: GameState
    winning_strategies: List[WinningStrategy]
    winner: Player
    pre_move_type: MoveType

    def __init__(self, players_list, dimension, winning_strategies):
        self.board = Board(dimension)
        self.players_list = players_list
        self.next_player_index = 0
        self.game_state = GameState.IN_PROGRESS
        self.winning_strategies = winning_strategies
        self.winner = None
        self.moves_list = []
        self.pre_move_type = MoveType.UNDO

    @staticmethod
    def builder():
        return Builder()

    def validate_move(self, cell: Cell) -> bool:
        row = cell.row
        col = cell.col

        if cell.cell_state == CellState.FILLED:
            return False

        if row < 0 or row >= self.board.size or col < 0 or col >= self.board.size:
            return False

        return True

    def make_move(self):
        player = self.players_list[self.next_player_index]

        print(f"This is {player.name}'s turn")
        (row, col) = player.execute_move(self.board)

        cell = Cell(row, col)

        if not self.validate_move(cell):
            raise InvalidMoveException("This Move is not valid. ending the game")

        cell.cell_state = CellState.FILLED
        cell.symbol = player.symbol
        self.board.board[cell.row][cell.col] = cell
        final_move = Move(cell, player)
        self.pre_move_type = MoveType.NORMAL
        self.moves_list.append(final_move)
        self.next_player_index = (self.next_player_index + 1) % len(self.players_list)

        if self.check_winner(final_move):
            self.winner = player
            self.game_state = GameState.ENDED
        elif len(self.moves_list) == (self.board.size * self.board.size):
            self.game_state = GameState.DRAW

    def check_winner(self, move: Move) -> bool:
        for winning_strategy in self.winning_strategies:
            if winning_strategy.check_winner(self.board, move):
                return True

        return False

    def undo(self):
        if self.pre_move_type == MoveType.UNDO:
            print("There should be at least one normal move to perform undo operation")
        else:
            move = self.moves_list.pop()
            cell = move.cell

            cell.cell_state = CellState.EMPTY
            cell.symbol = None

            for winning_strategy in self.winning_strategies:
                winning_strategy.handle_undo(self.board, move)

            self.next_player_index = (
                self.next_player_index - 1 + len(self.players_list)
            ) % len(self.players_list)
            self.pre_move_type = MoveType.UNDO
