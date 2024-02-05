from machine_coding.tic_tac_toe.controllers.Game_Controller import GameController
from machine_coding.tic_tac_toe.models.Bot import Bot
from machine_coding.tic_tac_toe.models.BotDifficulty import BotDifficulty
from machine_coding.tic_tac_toe.models.GameState import GameState
from machine_coding.tic_tac_toe.models.Player import Player
from machine_coding.tic_tac_toe.models.Symbol import Symbol
from machine_coding.tic_tac_toe.strategies.winning_strategies.ColWinningStrategy import (
    ColWinningStrategy,
)
from machine_coding.tic_tac_toe.strategies.winning_strategies.DiagonalWinningStrategy import (
    DiagonalWinningStrategy,
)
from machine_coding.tic_tac_toe.strategies.winning_strategies.RowWinningStrategy import (
    RowWinningStrategy,
)


def main():
    print("Lets begin Game\n")
    game_controller = GameController()

    print("Please Enter Board size: \n")
    dim = int(input())

    players_list = [Player("Abhishek", Symbol("X")), Bot(BotDifficulty.EASY)]
    win_strategies = [
        RowWinningStrategy(),
        DiagonalWinningStrategy(),
        ColWinningStrategy(),
    ]

    game = game_controller.start_game(dim, players_list, win_strategies)

    while game_controller.game_state(game) == GameState.IN_PROGRESS:
        game_controller.display_board(game)

        print("\n")
        print("Please enter yes if you would like to undo previous operation")
        undo_in = input()

        if undo_in == "yes":
            game_controller.undo(game)
            continue

        game_controller.make_move(game)

    if game_controller.game_state(game) == GameState.ENDED:
        print(f"Winner of the game: {game_controller.get_winner(game)}")
    else:
        print("game ended in draw")


if __name__ == "__main__":
    main()
