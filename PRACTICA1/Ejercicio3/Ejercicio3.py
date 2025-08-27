import math

# Función que calcula el promedio
def promedio(valores):
    return sum(valores) / len(valores)

# Función que calcula la desviación estándar
def desviacion(valores):
    prom = promedio(valores)
    suma = sum((x - prom) ** 2 for x in valores)
    return math.sqrt(suma / (len(valores) - 1))

# Programa principal
def main():
    # Pedimos los valores al usuario
    entrada = input("Ingrese los números separados por espacio: ")
    valores = list(map(float, entrada.split()))
    
    prom = promedio(valores)
    desv = desviacion(valores)

    print(f"\nEl promedio es {prom:.2f}")
    print(f"La desviación estándar es {desv:.6f}")

# Ejecutamos
if __name__ == "__main__":
    main()
