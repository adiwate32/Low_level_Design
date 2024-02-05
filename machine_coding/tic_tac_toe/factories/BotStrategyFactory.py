from machine_coding.tic_tac_toe.models.BotDifficulty import BotDifficulty
from machine_coding.tic_tac_toe.strategies.bot_strategies.EasyBotStrategy import (
    EasyBotStrategy,
)


class BotStrategyFactory:
    @staticmethod
    def get_strategy(difficulty: BotDifficulty):
        if difficulty == BotDifficulty.EASY:
            return EasyBotStrategy()
