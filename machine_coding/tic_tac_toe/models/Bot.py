from machine_coding.tic_tac_toe.models.Board import Board
from machine_coding.tic_tac_toe.models.BotDifficulty import BotDifficulty
from machine_coding.tic_tac_toe.models.Player import Player
from machine_coding.tic_tac_toe.models.Symbol import Symbol
from machine_coding.tic_tac_toe.strategies.bot_strategies.BotStrategy import BotStrategy
from machine_coding.tic_tac_toe.factories.BotStrategyFactory import BotStrategyFactory


class Bot(Player):
    difficulty: BotDifficulty
    bot_strategy: BotStrategy

    def __init__(self, difficulty):
        self.difficulty = difficulty
        super().__init__("Bot", Symbol("O"))
        self.bot_strategy = BotStrategyFactory.get_strategy(difficulty)

    def execute_move(self, board: Board) -> tuple:
        return self.bot_strategy.bot_move(board)
