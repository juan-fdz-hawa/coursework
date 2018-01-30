from operator import add, sub


def a_plus_abs_b(a, b):
    """"Returns a plus the absolute value of b

    >>> a_plus_abs_b(5, -5)
    10
    >>> a_plus_abs_b(5, 5)
    10
    """
    if b < 0:
        f = sub
    else:
        f = add

    return f(a, b)


def two_of_three(a, b, c):
    """" Returns the sum of the squares of the two largest
    integers

    >>> two_of_three(2, 3, 1)
    13
    >>> two_of_three(2, 2, 1)
    8
    """
    return max(a * a + b * b, a * a + c * c, b * b + c * c)


def largest_factor(n):
    """"Return the largest factor that is smaller than n
    >>> largest_factor(8)
    4
    >>> largest_factor(7)
    1
    >>> largest_factor(10)
    5
    """
    assert n > 1

    k = n - 1
    while k >= 1:
        if n % k == 0:
            return k
        k -= 1


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


def c():
    return True


def t():
    return 1 / 0


def f():
    return 42


def with_if_function():

    if c():
        return t()
    else:
        return f()


def is_even(n):
    """"Returns True if number is even
    >>> is_even(2)
    True
    >>> is_even(7)
    False
    """
    return n % 2 == 0


def is_odd(n):
    return not is_even(n)


def hailstone(n, steps=1):
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
        return steps

    if is_even(n):
        return hailstone(n // 2, steps + 1)

    else:
        return hailstone(n * 3 + 1, steps + 1)

