# Backend Learning Journey — Java Fundamentals

Recap-level Java refresher before moving to Spring Boot. Notes below cover what was built, what broke, and why — not just a task list.

## Setup
- JDK 23, IntelliJ IDEA Community
- Plain Java project (no Maven/Gradle) — build tools deferred until Spring Boot, where Spring Initializr generates them anyway
- Compiling/running manually via `javac`/`java` from terminal, not IDE run button, to actually see compiler output

## Topics Covered

### 1. Hello World — compiler vs runtime errors
- Misspelled `main` or missing `public` → compiles fine, fails at runtime (`Main method not found`). The JVM enforces the exact signature `public static void main(String[] args)`; it can't tell you *which* part is wrong.
- Missing semicolon → fails at compile time (`javac` catches it before anything runs).
- **Lesson:** syntax errors are compile-time; semantically-invalid-but-grammatically-fine code (like a missing `main`) only surfaces at runtime.

### 2. Static typing — int vs double division
- `int a=5, b=2; a/b` → `2` (integer division truncates, doesn't round)
- `double c=5, d=2; c/d` → `2.5`
- `(double) a / b` → forces one operand to double *before* division happens, giving `2.5`
- **Lesson:** the operation itself is integer division when both operands are `int` — the fraction is discarded during the calculation, not after.

### 3. Scanner input — the buffer bug
- `nextInt()` leaves a trailing `\n` in the input buffer.
- Calling `nextLine()` right after `nextInt()` reads that leftover empty line instead of waiting for new input → `charAt(0)` on an empty string throws `StringIndexOutOfBoundsException`.
- **Fix:** insert a throwaway `sc.nextLine();` after `nextInt()`/`nextDouble()` calls to consume the leftover newline before reading a line-based token.

### 4. Git / .gitignore — UTF-16 BOM issue
- `.gitignore` created via PowerShell `echo >`/`>>` was silently written as **UTF-16 with a BOM** (`FF FE` byte prefix), not UTF-8.
- `type .gitignore` displayed it correctly (Windows handles the encoding transparently), but `git` couldn't match any ignore pattern against it — so `.class`/`.idea/` files kept getting tracked despite a seemingly correct `.gitignore`.
- **Fix:** recreate the file explicitly as UTF-8 without BOM:
  ```powershell
  [System.IO.File]::WriteAllLines("$PWD\.gitignore", @(".idea/", "out/", "*.class"), [System.Text.UTF8Encoding]::new($false))
  ```
- **Lesson:** `.gitignore` only stops *new* untracked files from being staged — already-tracked files need `git rm --cached` regardless of ignore rules. Also: explicitly running `git add <specific file>` overrides ignore rules entirely.

### 5. Packages
- Package declaration (`package banking;`) must be the first line of the file, and the folder structure must physically mirror the package name (`src/banking/ClassName.java`).
- Compile from the parent of the package folder: `javac banking\*.java`
- Run with the fully-qualified name: `java banking.Main` (not just `java Main`)

### 6. OOP — encapsulation & inheritance
Built `BankAccount` (private fields, public deposit/withdraw, no direct balance setter) and `SavingsAccount extends BankAccount` (adds `interestRate`, overrides `withdraw` to enforce a $500 minimum via `super.getBalance()`/`super.withdraw()`).
- **Access modifier lesson:** `protected` allows same-package access too, not just subclasses — so same-folder code "accidentally" compiles even when `public` was the intended/correct modifier. Real projects split into packages (`controller`/`service`/`repository`, matching Spring Boot's structure), where this distinction actually bites.

### 7. Interfaces — decoupling
Built `Transaction` interface with `execute(BankAccount account)`, implemented by `DepositTransaction` and `WithdrawTransaction`. Looped through a `Transaction[]` calling `.execute()` polymorphically.
- **Why this matters:** the calling code (`Main`'s loop) has zero knowledge of which concrete class it's running — it only knows "this is a `Transaction`." Adding a new transaction type never requires touching the loop.
- **Direct link to Spring Boot:** this is the hand-built version of what `@Autowired` does — injecting *some* implementation of an interface without the caller knowing or caring which one.

### 8. Abstract classes — shared state, and getter/setter discipline
Refactored `BankAccount`/`SavingsAccount` to extend `abstract class Account`, which holds private `accountNumber`/`balance` fields, concrete `deposit()`, and an abstract `withdraw()` that each subclass must define.
- **Mutation bug caught along the way:** a getter (`getBalance()`) only *returns* a value — it can't be used to mutate state (`super.getBalance() -= amount` doesn't work). Subclasses need a `protected setBalance()` to change parent state without touching the private field directly.
- **Why go through setters even from a subclass** (even though `protected` fields would technically allow direct access): if `Account` later adds validation or logging inside `setBalance()`, every subclass benefits automatically. Direct field mutation bypasses that silently.

### 9. Interface vs abstract class — when to use which
Built both `Transaction` (interface) and `Account` (abstract class) and compared them directly:
- **Abstract class** fits an "is-a" relationship with **shared state**: `BankAccount`/`SavingsAccount` both genuinely *have* a balance and account number, plus share real behavior (`deposit()`).
- **Interface** fits a "can-do" relationship with **no shared state**: `DepositTransaction`/`WithdrawTransaction` share nothing structurally except "both are executable" — no common fields, no common base behavior.
- **Rule of thumb:** shared state + partial shared behavior → abstract class. Pure contract, unrelated implementations → interface.
- Java also only allows single class inheritance but multiple interface implementation — a practical reason interfaces are the only option when a class needs to fulfill several unrelated contracts at once.

### 10. Custom exceptions
- `InsufficientBalanceException extends Exception` (checked), thrown from `withdraw()` instead of printed.
- Checked exceptions propagate through every layer — `Transaction.execute()` needed `throws` too since it calls a throwing method.
- Per-transaction `try-catch` (not one around the whole loop) — one failed transaction shouldn't abort the rest.
- `e.getMessage()`, not `println(e)` — avoids leaking internal class name in output.
- Bug caught: `execute()` was typed to `BankAccount` not `Account`, silently breaking polymorphism from Task 6/7. Fixed by typing to the supertype.

## Next Up
- Maven/Gradle, Spring Boot fundamentals