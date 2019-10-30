package Models;

abstract public  class Card {
    public String Name;
    public User Author;
    public Description Description;
    public Iterable<User> Members;
    public Place Place;
}
