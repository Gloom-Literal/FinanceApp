package com.example.financeapp

internal data class MainUiState(
    // Какое начальное значение у баланса?
    val balanceValue: Int = 0,
    // Как посчитать выполненные операции?
    val counter: Int = 0,
)

internal enum class TransactionType {
    // Какие два типа нужны кнопкам?
    DEPOSIT,
    WITHDRAW,
}

internal data class Transaction(
    // Какие два свойства полностью описывают операцию?
    val amount: Int = 0,
    val type: TransactionType,
)

internal sealed interface MainUiEvent {
    // Одно событие должно переносить  : созданную транзакцию
    data class AddTransaction(val transaction: Transaction) : MainUiEvent
}

internal fun reduce(
    state: MainUiState,
    event: MainUiEvent,
): MainUiState = when (event) {
    is MainUiEvent.AddTransaction -> {
        val balanceChanged: Int = when (event.transaction.type) {
            TransactionType.DEPOSIT -> state.balanceValue + event.transaction.amount
            TransactionType.WITHDRAW -> state.balanceValue - event.transaction.amount
        }
        state.copy(balanceValue = balanceChanged, counter = state.counter + 1)
    }
    // Получите данные события и верните новое состояние
}
