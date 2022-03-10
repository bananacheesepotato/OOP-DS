package a1;

/** NetId: awn33. Time spent: 5 hours, 0 minutes. <br>
 * What I thought about this assignment: Fun and a good lesson on assert statements, but tedious to
 * write out all the cases.<br>
 * <br>
 * An instance maintains info about an Elephant. */
public class Elephant {

    /** name of elephant */
    private String name;

    /** birth year of elephant */
    private int birthYear;

    /** birth month of elephant in 1..12, with 1 meaning January, etc. */
    private int birthMonth;

    /** gender of elephant, M(ale) or F(emale) */
    private char gender;

    /** mother of elephant */
    private Elephant mother;

    /** father of elephant */
    private Elephant father;

    /** number of known children of elephant */
    private int offspring;

    /** Constructor: an instance with nickname n, gender g, birth year y, and birth month m. Its
     * parents are unknown, and it has no children. Precondition: n has at least 1 character, y >=
     * 2000, m is in 1..12, and g is 'F' for female or 'M' for male. */
    public Elephant(String n, char g, int y, int m) {
        assert n != null && n.length() >= 1 && y >= 2000 && m >= 1 && m <= 12 &&
            (g == 'M' || g == 'F');
        name= n;
        birthYear= y;
        birthMonth= m;
        gender= g;
    }

    /** this elephant's nickname */
    public String name() {
        return name;
    }

    /** the value of "this elephant is a female" */
    public boolean isFemale() {
        return gender == 'F';
    }

    /** date of birth of elephant in the form "month/year", with no blanks */
    public String date() {
        return birthMonth + "/" + birthYear;
    }

    /** this elephant's mother (null if unknown) */
    public Elephant mom() {
        return mother;
    }

    /** this elephant's father (null if unknown) */
    public Elephant dad() {
        return father;
    }

    /** the number of children of this elephant */
    public int children() {
        return offspring;
    }

    /** Add e as this elephant's mother. Precondition: this elephant’s mother is unknown and e is
     * female. */
    public void addMom(Elephant e) {
        assert mother == null && e != null && e.isFemale();
        e.offspring+= 1;
        mother= e;
    }

    /** Add e as this elephant's father. Precondition: This elephant's father is unknown and e is
     * male. */
    public void addDad(Elephant e) {
        assert father == null && e != null && !e.isFemale();
        e.offspring+= 1;
        father= e;
    }

    /** Constructor: an elephant with nickname n, gender g, birth year y, birth month m, mother ma,
     * and father pa. Precondition: n has at least 1 character, y >= 2000, g is 'F' for female or
     * 'M' for male, m is in 1..12, ma is a female, and pa is a male */
    public Elephant(String n, char g, int y, int m, Elephant ma, Elephant pa) {
        assert n != null && n.length() >= 1 && y >= 2000 && m >= 1 && m <= 12 &&
            (g == 'M' || g == 'F') && ma.isFemale() && !pa.isFemale() && ma != null && pa != null;

        name= n;
        birthYear= y;
        birthMonth= m;
        gender= g;
        addMom(ma);
        addDad(pa);
    }

    /** Return value of "this elephant is older than e." Precondition: e is not null. */
    public boolean isOlder(Elephant e) {
        assert e != null;
        return birthYear == e.birthYear && birthMonth < e.birthMonth || birthYear < e.birthYear;
    }

    /** Return value of "this elephant and e are siblings." (note: if e is null they can't be
     * siblings, so false is returned). */
    public boolean areSibs(Elephant e) {
        return e != null && this != e && (mother == e.mother && mother != null ||
            father == e.father && father != null);
    }
}
