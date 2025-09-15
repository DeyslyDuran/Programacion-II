
import math

class AlgebraVectorial:
    def __init__(self, *componentes):
        self.vec = list(componentes)

    def __str__(self):
        return f"{tuple(self.vec)}"

    def __add__(self, other):
        return AlgebraVectorial(*[a + b for a, b in zip(self.vec, other.vec)])

    def __sub__(self, other):
        return AlgebraVectorial(*[a - b for a, b in zip(self.vec, other.vec)])

    def __mul__(self, other):
        """Producto punto (dot product)"""
        return sum(a * b for a, b in zip(self.vec, other.vec))

    def __xor__(self, other):
        """Producto cruz (solo válido en 3D)"""
        if len(self.vec) == 3 and len(other.vec) == 3:
            x1, y1, z1 = self.vec
            x2, y2, z2 = other.vec
            return AlgebraVectorial(y1*z2 - z1*y2,
                                    z1*x2 - x1*z2,
                                    x1*y2 - y1*x2)
        else:
            raise ValueError("El producto cruz solo está definido en 3D")

    def norma(self):
        return math.sqrt(sum(a*a for a in self.vec))

    # -------- Métodos solicitados -------- #

    def es_perpendicular(self, other):
        return self * other == 0

    def es_paralelo(self, other):
        # Producto cruz = 0 en 3D, o razón constante en 2D
        if len(self.vec) == 3 and len(other.vec) == 3:
            return (self ^ other).norma() == 0
        else:  
            # Para 2D verificamos proporción
            try:
                r = self.vec[0]/other.vec[0]
                return all(abs(self.vec[i] - r*other.vec[i]) < 1e-9 
                           for i in range(len(self.vec)))
            except ZeroDivisionError:
                return False

    def proyeccion_sobre(self, other):
        """Proyección de self sobre other"""
        factor = (self * other) / (other.norma()**2)
        return AlgebraVectorial(*[factor*b for b in other.vec])

    def componente_en(self, other):
        """Componente escalar de self en la dirección de other"""
        return (self * other) / other.norma()


# ------------------- Ejemplo de uso ------------------- #
a = AlgebraVectorial(2, 3, 1)
b = AlgebraVectorial(4, 6, 2)

print("Vector a:", a)
print("Vector b:", b)

print("a · b =", a * b)
print("a × b =", a ^ b)

print("¿a ⟂ b?", a.es_perpendicular(b))
print("¿a ∥ b?", a.es_paralelo(b))

print("Proy_b(a) =", a.proyeccion_sobre(b))
print("Comp_b(a) =", a.componente_en(b))
