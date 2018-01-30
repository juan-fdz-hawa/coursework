def is_tree(t):
    if type(t) != list or len(t) < 1:
        return False
    return all([is_tree(b)] for b in get_branches(t))


def is_leaf(t):
    return not get_branches(t)


def get_branches(t):
    return t[1:]


def get_root(t):
    return t[0]


def tree(root, branches=[]):
    all([is_tree(b) for b in branches])
    return [root] + list(branches)


def count_leaves(t):
    if is_leaf(t):
        return 1
    return sum([count_leaves(b) for b in get_branches(t)])