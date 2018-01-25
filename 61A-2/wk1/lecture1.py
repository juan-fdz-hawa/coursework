# An expression describes a computation and evaluates to a value.
# All expressions can use function call notation.

from operator import add, mul

mul(add(2, mul(4, 6)), add(3, 5))

# An expression consist of: operator ( operand, operand, ...)
# where both operator and operand can be expressions.

# Types of expressions:
    # Primitive expressions
    # Call expressions

# Computer programs consist of expressions and statements:
#  Statements perform some action
#  Expressions produce a value through some computation.

# Computer programs consist of data and functions

# All expressions must be evaluated in an environment.

# Scope: "area" where a name is valid.

# To master the use of a functional abstraction (where we are not concerned
# with the implementation details, only whatever computations it performs).
# Is useful to consider a function in terms of its three core attributes:
# It's domain (inputs)
# It's range (outputs)
# It's intent (the mapping between inputs and outputs).

# At the end of the day the only thing the interpreter does is
# execute statements.
# An statement tells the interpreter to do something, it changes its state somehow.
# and expression is a computation for an abstraction.