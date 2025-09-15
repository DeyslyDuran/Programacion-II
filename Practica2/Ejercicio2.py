import math


class Vector3D:

    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

    # a) Suma de dos vectores (a + b)
    def __add__(self, other):
        return Vector3D(self.x + other.x, self.y + other.y, self.z + other.z)

    # Resta de dos vectores (a - b)
    def __sub__(self, other):
        return Vector3D(self.x - other.x, self.y - other.y, self.z - other.z)

    # b) Multiplicación de un escalar por un vector (r * a)
    def __mul__(self, other):
        if isinstance(other, (int, float)):
            return Vector3D(self.x * other, self.y * other, self.z * other)

        # e) Producto escalar (a . b)
        elif isinstance(other, Vector3D):
            return self.x * other.x + self.y * other.y + self.z * other.z

        raise TypeError("Tipo de operando no soportado para la multiplicación")

    # b) Multiplicación de un escalar por un vector (r * a) cuando el escalar está primero
    def __rmul__(self, other):
        return self.__mul__(other)

    # d) Normal de un vector (a / |a|) - Implementado como método
    def normal(self):
        mag = self.magnitude()
        if mag == 0:
            return Vector3D(0, 0, 0)
        return Vector3D(self.x / mag, self.y / mag, self.z / mag)

    # c) Longitud (magnitud) de un vector
    def magnitude(self):
        return math.sqrt(self.x ** 2 + self.y ** 2 + self.z ** 2)

    # f) Producto vectorial (a x b)
    def cross_product(self, other):
        new_x = self.y * other.z - self.z * other.y
        new_y = self.z * other.x - self.x * other.z
        new_z = self.x * other.y - self.y * other.x
        return Vector3D(new_x, new_y, new_z)

    # Verifica si dos vectores son perpendiculares
    def is_perpendicular(self, other):
        return self.x * other.x + self.y * other.y + self.z * other.z == 0

if __name__ == "__main__":
    print("--- Ejemplo de uso de la clase Vector3D ---")

    # Definición de vectores
    a = Vector3D(1, 2, 3)
    b = Vector3D(4, 5, 6)

    print(f"Vector a: {a}")
    print(f"Vector b: {b}")
    print("-" * 30)

    # a) Suma de dos vectores
    suma = a + b
    print(f"Suma de a y b (a + b): {suma}")

    # b) Multiplicación de un escalar por un vector
    escalar = 2
    mult_escalar = a * escalar
    print(f"Multiplicación de a por {escalar} (a * {escalar}): {mult_escalar}")

    # c) Longitud de un vector
    longitud_a = a.magnitude()
    print(f"Longitud del vector a: {longitud_a:.2f}")

    # d) Normal de un vector
    normal_a = a.normal()
    print(f"Vector normal de a: {normal_a}")

    # e) Producto escalar
    prod_escalar = a * b
    print(f"Producto escalar de a y b (a . b): {prod_escalar}")

    # f) Producto vectorial
    prod_vectorial = a.cross_product(b)
    print(f"Producto vectorial de a y b (a x b): {prod_vectorial}")

    # Verificación de perpendicularidad
    print("-" * 30)
    v1 = Vector3D(1, 0, 0)
    v2 = Vector3D(0, 1, 0)
    print(f"¿Los vectores {v1} y {v2} son perpendiculares?: {v1.is_perpendicular(v2)}")