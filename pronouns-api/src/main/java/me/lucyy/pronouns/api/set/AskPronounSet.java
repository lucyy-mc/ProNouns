package me.lucyy.pronouns.api.set;

/**
 * A special pronoun set indicating that the user wishes to be asked for their pronouns.
 * For the purpose of placeholders, it uses another set as a base.
 *
 * @since 1.3.0
 * @author lucy
 */
public class AskPronounSet extends PronounSet {
	/**
	 * @param base the pronoun set to use for placeholders
	 */
	public AskPronounSet(PronounSet base) {
		super(base.getSubjective(), base.getObjective(), base.getProgressive(), base.getPossessiveAdjective(),
				base.getPossessivePronoun(), base.getReflexive());
	}

	/**
	 * @return the string "Ask"
	 */
	@Override
	public String getShortenedName() {
		return "Ask";
	}

	/**
	 * @return the string "Ask"
	 */
	@Override
	public String getName() {
		return "Ask";
	}

	/**
	 * @return the string "Ask"
	 */
	@Override
	public String toString() {
		return "Ask";
	}
}
