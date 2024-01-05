package com.example.nameanalysis

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit Test Rule for setting a test dispatcher in coroutine-based unit tests.
 *
 * This rule overrides the main dispatcher used in coroutines with a test dispatcher, allowing for more controlled
 * testing of coroutine-based logic. The dispatcher is set before each test starts and reset after each test finishes.
 *
 * @property testDispatcher The [TestDispatcher] used to override the main coroutine dispatcher during tests.
 *                          Defaults to [UnconfinedTestDispatcher] for unconfined execution of coroutines in tests.
 */
class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {

    /**
     * Sets the main coroutine dispatcher to [testDispatcher] before each test starts.
     *
     * @param description Describes the test that is about to be run (unused in this implementation).
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Resets the main coroutine dispatcher to its original state after each test finishes.
     *
     * @param description Describes the test that has just been run (unused in this implementation).
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }

}