package secret;

import utils.GlobalFunctions;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
	// keycodes for konami code
	// 57416 up
	// 57419 left
	// 57424 down
	// 57421 right
	// 57424 down
	// 28 enter
	private int[] konamiCode = {57416, 57416, 57424, 57424, 57419, 57421, 57419, 57421, 28};
	private int konamiIndex = 0;

	public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == konamiCode[konamiIndex]) {
			konamiIndex++;
		} else {
			konamiIndex = 0;
		}

		if (konamiIndex == konamiCode.length) {
			konamiIndex = 0;
			GlobalFunctions.clearScreen();
			System.out.println("Konami code activated");
			GlobalFunctions.pressToContinue();
		}
	}

	public GlobalKeyListener() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.out.println(ex.getMessage());
		}
		GlobalScreen.addNativeKeyListener(this);
	}
}