package ru.otus.emulator;

import com.google.inject.Singleton;
import lombok.SneakyThrows;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Singleton
public class EmulatorProvider {
  //создаем блокирующую очередь выдачи тестам эмуляторов на базе тех, что прописаны в классе Emulator
  private final BlockingQueue<Emulator> emulators =
      new ArrayBlockingQueue<>(Emulator.values().length, false, Arrays.asList(Emulator.values()));
  //переменная хранит отдельное значение эмулятора для каждого потока
  private final ThreadLocal<Emulator> currentEmulators = new ThreadLocal<>();

  //забирает свободный эмулятор из очереди, записывает его значение в currentEmulators и возвращает(return) его же
  @SneakyThrows
  public Emulator takeAndGet() {
    //emulator - эмулятор, который мы взяли из очереди
    Emulator emulator = emulators.poll(2, TimeUnit.MINUTES);
    currentEmulators.set(emulator);
    return emulator;
  }

  //возвращает текущее значение эмулятора для текущего потока
  public Emulator get() {
    return currentEmulators.get();
  }

  //возвращает в очередь эмулятор текущего потока
  @SneakyThrows
  public void putBack() {
    emulators.offer(get(), 2, TimeUnit.MINUTES);
  }
}
