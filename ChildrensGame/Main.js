// Link útil para teste de mesa automático do algoritmo:
// https://staying.fun/en/features/algorithm-visualize

// Resolução do problema "Children's Game" (UVa 10905)
// O objetivo é formar o maior número possível ao concatenar os números fornecidos.
// Existem duas abordagens: manual (bubble sort customizado) e automática (sort com localeCompare).

// Função manual que simula o algoritmo de ordenação customizada (equivalente ao compareTo do Java)
// Essa função compara pares de números e troca se a concatenação invertida for maior.
function customSortGame(n, numbers) {
  for (let i = 0; i < n - 1; i++) {
    for (let j = 0; j < n - 1 - i; j++) {
      const a_b = numbers[j] + numbers[j + 1];
      const b_a = numbers[j + 1] + numbers[j];
      if (b_a > a_b) {
        let temp = numbers[j];
        numbers[j] = numbers[j + 1];
        numbers[j + 1] = temp;
      }
    }
  }
  return numbers.join('');
}

// Exemplo de entrada fixa
const n = 4;
const numbers = ["123", "124", "56", "90"]; 

// Usando a função manual
const result = customSortGame(n, numbers);
console.log("Resultado com função manual:", result); // Saída esperada: 9056124123

// ---
// Alternativamente, pode-se usar o método sort do JS com localeCompare,
// que equivale ao uso de sorted/compareTo em Java para ordenar automaticamente.
// A lógica é a mesma: comparar as concatenações para decidir a ordem.

// Valores fixos para entrada conforme seu exemplo
const n2 = 4;
const numbers2 = ["123", "124", "56", "90"];

// Ordena os números usando a lógica personalizada
numbers2.sort((a, b) => (b + a).localeCompare(a + b));

// Junta os números ordenados para formar o maior número possível
const result2 = numbers2.join('');

console.log("Resultado com sort automático:", result2);  // Saída esperada: 9056124123
