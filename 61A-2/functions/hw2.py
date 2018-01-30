from operator import add, sub, mul


def square(x):
    return x * x


def triple(x):
    return x * x * x


def identity(x):
    return x


def increment(x):
    return x + 1


def product(n, term):
    """Return the product of the first n terms in a sequence.

    n    -- a positive integer
    term -- a function that takes one argument

    >>> product(3, identity) # 1 * 2 * 3
    6
    >>> product(5, identity) # 1 * 2 * 3 * 4 * 5
    120
    >>> product(3, square)   # 1^2 * 2^2 * 3^2
    36
    >>> product(5, square)   # 1^2 * 2^2 * 3^2 * 4^2 * 5^2
    14400
    """
    k, total = 1, 1
    while k <= n:
        total *= term(k)
        k += 1
    return total


def factorial(n):
    """Return n factorial for n >= 0 by calling product.

    >>> factorial(4)
    24
    >>> factorial(6)
    720
    """
    if n == 0 or n == 1:
        return 1
    return n * product(n - 1, identity)


def make_adder(n):
    """Return a function that takes an argument K and returns N + K.

    >>> add_three = make_adder(3)
    >>> add_three(1) + add_three(2)
    9
    >>> make_adder(1)(2)
    3
    """
    return lambda x: x + n

