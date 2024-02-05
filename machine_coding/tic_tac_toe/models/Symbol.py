class Symbol:
    __char: str

    def __init__(self, char: str):
        self.char = char

    @property
    def char(self):
        return self.__char

    @char.setter
    def char(self, value):
        if not isinstance(value, str) or len(value) != 1:
            raise ValueError("char must be a single character")
        self.__char = value
