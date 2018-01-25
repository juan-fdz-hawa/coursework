from operator import add, sub


def a_plus_abs_b(a, b):
    """"Return a+abs(b), but without calling abs

    >>> a_plus_abs_b(2, 5)
    7
    >>> a_plus_abs_b(2, -5)
    7
    """
    if b < 0:
        f = sub
    else:
        f = add

    return f(a, b)


def square(a):
    """"Returns a*a

    >>> square(2)
    4
    >>> square(-10)
    100
    """
    return a * a


def two_of_three(a, b, c):
    """"Return a*a + b*b if a and b are the two
    largest numbers in {a, b, c}
    >>> two_of_three(1, 2, 3)
    13
    >>> two_of_three(2, 2, 1)
    8
    """
    return max(a*a + b*b, a*a + c*c, b*b + c*c)


def largest_factor(n):
    """"Returns the largest factor of n, that is less than n

    >>> largest_factor(15)
    5
    >>> largest_factor(7)
    1
    >>> largest_factor(0)
    0
    >>> largest_factor(80)
    40
    """
    i, last_factor = 1, 0
    while i < n:
        if n % i == 0:
            last_factor = i
        i = i + 1
    return last_factor


def if_function(condition, true_result, false_result):
    """Return true_result if condition is a true value, and
    false_result otherwise.

    >>> if_function(True, 2, 3)
    2
    >>> if_function(False, 2, 3)
    3
    >>> if_function(3==2, 3+2, 3-2)
    1
    >>> if_function(3>2, 3+2, 3-2)
    5
    """
    if condition:
        return true_result
    else:
        return false_result


def with_if_statement():
    """
    >>> with_if_statement()
    1
    """
    if c():
        return t()
    else:
        return f()


def with_if_function():
    """
    >>> with_if_function()
    1
    """
    return if_function(c(), t(), f())


def c():
    return True


def t():
    return 1


def f():
    return 1 / 0


def hailstone(n, seq_length = 1):
    """Print the hailstone sequence starting at n and return its
    length.

    >>> a = hailstone(10)
    10
    5
    16
    8
    4
    2
    1
    >>> a
    7
    """
    print(n)
    if n == 1:
        return seq_length
    elif n % 2 == 0:
        return hailstone(n // 2, seq_length + 1)
    else:
        return hailstone(n * 3 + 1, seq_length + 1)
