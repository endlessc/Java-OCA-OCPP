/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all
   copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.
*/

package eu.chargetime.ocpp.v201.model.messages;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.RequestWithId;
import eu.chargetime.ocpp.utilities.MoreObjects;
import eu.chargetime.ocpp.v201.model.types.CustomData;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * UnpublishFirmwareRequest
 *
 * <p>OCPP 2.0.1 FINAL
 */
public final class UnpublishFirmwareRequest extends RequestWithId {
  /** Custom data */
  @Nullable private CustomData customData;

  /** The MD5 checksum over the entire firmware file as a hexadecimal string of length 32. */
  private String checksum;

  /**
   * Constructor for the UnpublishFirmwareRequest class
   *
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32.
   */
  public UnpublishFirmwareRequest(String checksum) {
    setChecksum(checksum);
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public UnpublishFirmwareRequest withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets the MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
   *
   * @return The MD5 checksum over the entire firmware file as a hexadecimal string of length 32
   */
  public String getChecksum() {
    return checksum;
  }

  /**
   * Sets the MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
   *
   * @param checksum The MD5 checksum over the entire firmware file as a hexadecimal string of
   *     length 32
   */
  public void setChecksum(String checksum) {
    if (!isValidChecksum(checksum)) {
      throw new PropertyConstraintException(checksum, "checksum is invalid");
    }
    this.checksum = checksum;
  }

  /**
   * Returns whether the given checksum is valid
   *
   * @param checksum the checksum to check the validity of
   * @return {@code true} if checksum is valid, {@code false} if not
   */
  private boolean isValidChecksum(String checksum) {
    return checksum != null && checksum.length() <= 32;
  }

  @Override
  public boolean validate() {
    return isValidCustomData(customData) && isValidChecksum(checksum);
  }

  @Override
  public boolean transactionRelated() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnpublishFirmwareRequest that = (UnpublishFirmwareRequest) o;
    return Objects.equals(customData, that.customData) && Objects.equals(checksum, that.checksum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, checksum);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("checksum", checksum)
        .add("isValid", validate())
        .toString();
  }
}
