package oop.homework1030;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class InfoPublisher<T> {
  private HashMap<String, ArrayList<Consumer<T>>> subscribers;
  
  public InfoPublisher() {
    this.subscribers = new HashMap<String, ArrayList<Consumer<T>>>();
  }

  public void subscribe(String event, Consumer<T> subscriber) {
    if (!subscribers.containsKey(event)) {
      this.subscribers.put(event, new ArrayList<Consumer<T>>());
    }

    this.subscribers.get(event).add(subscriber);
  }

  public void publish(String event, T data) {
    if (!subscribers.containsKey(event))
      return;

    subscribers.get(event)
      .forEach(consumer -> consumer.accept(data));
  }
}
