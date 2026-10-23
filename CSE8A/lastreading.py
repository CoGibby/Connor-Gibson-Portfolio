# t is a time in seconds, as a float value.
def radioactive_decay(t):
    N0 = 1000 # initial quantity, fixed
    lam = 0.00012 # decay constant (per second)
    
    # Approximate e^x
    x = -lam * t
    exp_approx = 1 + x + (x**2)/2 + (x**3)/6 + (x**4)/24 + (x**5)/120

    # Decay formula: N = N0 * e^(-lam * t)
    N = N0 * exp_approx
    return N
print(radioactive_decay(10000))
print(radioactive_decay(1))
print(radioactive_decay(0))
