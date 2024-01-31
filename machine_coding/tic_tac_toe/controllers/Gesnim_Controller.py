from typing import List

from machine_coding.tic_tac_toe.models.Game import Game
from machine_coding.tic_tac_toe.models.GameState import GameState
from machine_coding.tic_tac_toe.models.Player import Player
from machine_coding.tic_tac_toe.strategies.winning_strategies.WinningStrategy import WinningStrategy


class GameController:

    def start_game(self, dimension, players_list: List[Player], winning_strategies: List[WinningStrategy]) -> Game:
        return Game.builder().set_dimension(dimension).set_players(players_list).set_winning_strategies(
            winning_strategies).build()

    def make_move(self, game: Game):
        game.make_move()

    def display_board(self, game: Game):
        game.board.display_board()

    def game_state(self, game: Game) -> GameState:
        return game.game_state

    def get_winner(self, game: Game) -> str:
        return game.winner.name

    def undo(self, game: Game):
        game.undo()
