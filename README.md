# ToyLanguage Interpreter

ToyLanguage is a fully featured interpreter for a custom-designed, toy programming language. It was created as an educational project to demonstrate key concepts in interpreter design, static type-checking, memory management, and concurrency. Its simplicity makes it ideal for learning, while its rich feature set provides a taste of modern programming language constructs.

## Features

- **Expressions and Operators**
  - **Arithmetic Expressions:** Support for addition, subtraction, multiplication, division, etc.
  - **Boolean Expressions:** Logical operators such as AND, OR, and NOT.
  - **Relational Expressions:** Comparisons (==, !=, <, >, <=, >=) to drive control flow.
  - **Custom Operators:** For example, `MUL(exp1, exp2)` is defined as `((exp1 * exp2) - (exp1 + exp2))` without considering operator precedence, allowing user-defined evaluation order.

- **Control Structures**
  - **Conditional Statements:** `if`, `switch`
  - **Loops:** `while`, `repeat-until`, and even `for` loops
  - **Compound Statements:** Nested execution via `CompStmt`

- **Concurrency**
  - **Thread Creation:** The `fork` statement allows parallel execution.
  - **Synchronization:** Built-in support for locks and semaphores.
    - **Lock Mechanism:** Ensures mutual exclusion for shared resources.
    - **Semaphore Support:** Controls access based on available permits.
  - **Symbol Table Stack:** Forked processes clone the entire symbol table stack, preserving caller contexts.

- **Memory Management**
  - **Heap Allocation:** Dynamic allocation of memory with operations for reading and writing.

- **Static Type-Checking**
  - Ensures that expressions, variables, and procedure parameters are consistently and correctly used before execution.

## Implementation Details

- **Expressions & Operators:**  
  All expressions (arithmetic, boolean, relational, custom) are implemented as classes in the `Model.Exp` package. This modular approach allows for flexible evaluation and easy extension.

- **Control Structures:**  
  Statements such as `if`, `switch`, `while`, and `repeat-until` are defined in the `Model.Statement` package. Compound statements (`CompStmt`) are used to nest operations, with each `CompStmt` accepting only two sub-statements.

- **Concurrency with Locks & Semaphores:**  
  The interpreter provides synchronized implementations of both locks and semaphores. Their methods (lookup, update, add, etc.) use Java's synchronization primitives to ensure that operations on shared resources are atomic and thread-safe.

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven or Gradle (optional, for build automation)

### Installation & Running

1. **Clone the repository:** 
   git clone https://github.com/Nanu25/ToyLanguage

2. **Navigate to the project directory:**
   cd toylanguage

3. **Compile and run:**
   -mvn compile
   -mvn exec:java -Dexec.mainClass="ToyLanguageApp"


## Photos

### 🟢 1. Program Selection  
At the beginning, you can select a program to run.  
![Program Selection](https://github.com/Nanu25/ToyLanguage/blob/main/ex.png)

### 🔄 2. Step-by-Step Execution  
You can see the program's execution step by step along with all the details: execution stack, symbol table, semaphore table, and more.  
![Execution Details](https://github.com/Nanu25/ToyLanguage/blob/main/prgrun.png)

### 🔍 3. Viewing Different Program States  
You can change the program state IDs to inspect each one's execution stack, symbol table, and other details.  
![Program State Selection](https://github.com/Nanu25/ToyLanguage/blob/main/threadchange.png)

### ✅ 4. Program Completion  
When the program is finished, the "Run" button has no effect.  
![Program Completion](https://github.com/Nanu25/ToyLanguage/blob/main/final.png)



