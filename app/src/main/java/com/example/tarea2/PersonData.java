package com.example.tarea2;

/**
 * Esta clase representa un personaje
 * Contiene la información sobre el personaje
 */
public class PersonData {
    private final String name;
    private final String whois;
    private final String description;
    private final String habilities;
    private final int image;

    /**
     * Constructor para crear un personaje
     *
     * @param name El nombre del personaje
     * @param whois Quien es el personaje
     * @param description Descripción del personaje
     * @param habilities Habilidades del personaje
     * @param image Foto del personaje
     */
   public PersonData( String name, String whois, String description,String habilities, int image) {
        this.name = name;
        this.whois=whois;
        this.description = description;
        this.habilities = habilities;
        this.image = image;
    }

    /**
     * Obtiene la descripción del personaje
     *
     * @return La descripción del personaje
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene la foto del personaje
     *
     * @return La foto del personaje
     */
    public int getImage() {
        return image;
    }

    /**
     * Obtiene las habilidades del personaje
     *
     * @return Las habilidades del personaje
     */
    public String getHabilities() {
        return habilities;
    }

    /**
     * Obtiene el nombre del personaje
     *
     * @return El nombre del personaje
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene quien es el personaje
     *
     * @return Quien es el personaje
     */
    public String getWhois() {
        return whois;
    }
}
