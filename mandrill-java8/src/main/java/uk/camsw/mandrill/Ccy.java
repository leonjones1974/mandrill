package uk.camsw.mandrill;

/**
 * Opted for my own currency Enum here as the set of supported currencies is small, we have no currency specific
 * business logic and because I prefer the compile time checking offered by the finite currency enum values over
 * runtime checking against a pre-defined set of supported currencies
 */
public enum Ccy {
    GBP,
    USD,
    EUR
}
