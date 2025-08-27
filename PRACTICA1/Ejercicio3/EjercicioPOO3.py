import math

class Estadistica:
    def __init__(self, valores):
        self.valores = valores

    def promedio(self):
        return sum(self.valores) / len(self.valores)

    def desviacion(self):
        prom = self.promedio()
        suma = sum((x - prom) ** 2 for x in self.valores)
        return math.sqrt(suma / (len(self.valores) - 1))


# Programa principal
def main():
    entrada = input("Ingrese los números separados por espacio: ")
    valores = list(map(float, entrada.split()))

    estad = Estadistica(valores)

    print(f"\nEl promedio es {estad.promedio():.2f}")
    print(f"La desviación estándar es {estad.desviacion():.6f}")

if __name__ == "__main__":
    main()
