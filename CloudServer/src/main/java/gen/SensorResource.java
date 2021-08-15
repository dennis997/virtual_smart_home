/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package gen;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
public class SensorResource implements org.apache.thrift.TBase<SensorResource, SensorResource._Fields>, java.io.Serializable, Cloneable, Comparable<SensorResource> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SensorResource");

  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField HUMIDITY_FIELD_DESC = new org.apache.thrift.protocol.TField("humidity", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField TEMP_FIELD_DESC = new org.apache.thrift.protocol.TField("temp", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField BRIGHTNESS_FIELD_DESC = new org.apache.thrift.protocol.TField("brightness", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField VOLUME_FIELD_DESC = new org.apache.thrift.protocol.TField("volume", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField TOPIC_FIELD_DESC = new org.apache.thrift.protocol.TField("topic", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SensorResourceStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SensorResourceTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String location; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String timestamp; // required
  public int humidity; // required
  public int temp; // required
  public int brightness; // required
  public int volume; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String topic; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LOCATION((short)1, "location"),
    TIMESTAMP((short)2, "timestamp"),
    HUMIDITY((short)3, "humidity"),
    TEMP((short)4, "temp"),
    BRIGHTNESS((short)5, "brightness"),
    VOLUME((short)6, "volume"),
    TOPIC((short)7, "topic");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LOCATION
          return LOCATION;
        case 2: // TIMESTAMP
          return TIMESTAMP;
        case 3: // HUMIDITY
          return HUMIDITY;
        case 4: // TEMP
          return TEMP;
        case 5: // BRIGHTNESS
          return BRIGHTNESS;
        case 6: // VOLUME
          return VOLUME;
        case 7: // TOPIC
          return TOPIC;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __HUMIDITY_ISSET_ID = 0;
  private static final int __TEMP_ISSET_ID = 1;
  private static final int __BRIGHTNESS_ISSET_ID = 2;
  private static final int __VOLUME_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LOCATION, new org.apache.thrift.meta_data.FieldMetaData("location", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HUMIDITY, new org.apache.thrift.meta_data.FieldMetaData("humidity", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TEMP, new org.apache.thrift.meta_data.FieldMetaData("temp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.BRIGHTNESS, new org.apache.thrift.meta_data.FieldMetaData("brightness", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.VOLUME, new org.apache.thrift.meta_data.FieldMetaData("volume", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOPIC, new org.apache.thrift.meta_data.FieldMetaData("topic", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SensorResource.class, metaDataMap);
  }

  public SensorResource() {
  }

  public SensorResource(
    java.lang.String location,
    java.lang.String timestamp,
    int humidity,
    int temp,
    int brightness,
    int volume,
    java.lang.String topic)
  {
    this();
    this.location = location;
    this.timestamp = timestamp;
    this.humidity = humidity;
    setHumidityIsSet(true);
    this.temp = temp;
    setTempIsSet(true);
    this.brightness = brightness;
    setBrightnessIsSet(true);
    this.volume = volume;
    setVolumeIsSet(true);
    this.topic = topic;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SensorResource(SensorResource other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetLocation()) {
      this.location = other.location;
    }
    if (other.isSetTimestamp()) {
      this.timestamp = other.timestamp;
    }
    this.humidity = other.humidity;
    this.temp = other.temp;
    this.brightness = other.brightness;
    this.volume = other.volume;
    if (other.isSetTopic()) {
      this.topic = other.topic;
    }
  }

  public SensorResource deepCopy() {
    return new SensorResource(this);
  }

  @Override
  public void clear() {
    this.location = null;
    this.timestamp = null;
    setHumidityIsSet(false);
    this.humidity = 0;
    setTempIsSet(false);
    this.temp = 0;
    setBrightnessIsSet(false);
    this.brightness = 0;
    setVolumeIsSet(false);
    this.volume = 0;
    this.topic = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getLocation() {
    return this.location;
  }

  public SensorResource setLocation(@org.apache.thrift.annotation.Nullable java.lang.String location) {
    this.location = location;
    return this;
  }

  public void unsetLocation() {
    this.location = null;
  }

  /** Returns true if field location is set (has been assigned a value) and false otherwise */
  public boolean isSetLocation() {
    return this.location != null;
  }

  public void setLocationIsSet(boolean value) {
    if (!value) {
      this.location = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getTimestamp() {
    return this.timestamp;
  }

  public SensorResource setTimestamp(@org.apache.thrift.annotation.Nullable java.lang.String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public void unsetTimestamp() {
    this.timestamp = null;
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return this.timestamp != null;
  }

  public void setTimestampIsSet(boolean value) {
    if (!value) {
      this.timestamp = null;
    }
  }

  public int getHumidity() {
    return this.humidity;
  }

  public SensorResource setHumidity(int humidity) {
    this.humidity = humidity;
    setHumidityIsSet(true);
    return this;
  }

  public void unsetHumidity() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HUMIDITY_ISSET_ID);
  }

  /** Returns true if field humidity is set (has been assigned a value) and false otherwise */
  public boolean isSetHumidity() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HUMIDITY_ISSET_ID);
  }

  public void setHumidityIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HUMIDITY_ISSET_ID, value);
  }

  public int getTemp() {
    return this.temp;
  }

  public SensorResource setTemp(int temp) {
    this.temp = temp;
    setTempIsSet(true);
    return this;
  }

  public void unsetTemp() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TEMP_ISSET_ID);
  }

  /** Returns true if field temp is set (has been assigned a value) and false otherwise */
  public boolean isSetTemp() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TEMP_ISSET_ID);
  }

  public void setTempIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TEMP_ISSET_ID, value);
  }

  public int getBrightness() {
    return this.brightness;
  }

  public SensorResource setBrightness(int brightness) {
    this.brightness = brightness;
    setBrightnessIsSet(true);
    return this;
  }

  public void unsetBrightness() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BRIGHTNESS_ISSET_ID);
  }

  /** Returns true if field brightness is set (has been assigned a value) and false otherwise */
  public boolean isSetBrightness() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BRIGHTNESS_ISSET_ID);
  }

  public void setBrightnessIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BRIGHTNESS_ISSET_ID, value);
  }

  public int getVolume() {
    return this.volume;
  }

  public SensorResource setVolume(int volume) {
    this.volume = volume;
    setVolumeIsSet(true);
    return this;
  }

  public void unsetVolume() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VOLUME_ISSET_ID);
  }

  /** Returns true if field volume is set (has been assigned a value) and false otherwise */
  public boolean isSetVolume() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VOLUME_ISSET_ID);
  }

  public void setVolumeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VOLUME_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getTopic() {
    return this.topic;
  }

  public SensorResource setTopic(@org.apache.thrift.annotation.Nullable java.lang.String topic) {
    this.topic = topic;
    return this;
  }

  public void unsetTopic() {
    this.topic = null;
  }

  /** Returns true if field topic is set (has been assigned a value) and false otherwise */
  public boolean isSetTopic() {
    return this.topic != null;
  }

  public void setTopicIsSet(boolean value) {
    if (!value) {
      this.topic = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case LOCATION:
      if (value == null) {
        unsetLocation();
      } else {
        setLocation((java.lang.String)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((java.lang.String)value);
      }
      break;

    case HUMIDITY:
      if (value == null) {
        unsetHumidity();
      } else {
        setHumidity((java.lang.Integer)value);
      }
      break;

    case TEMP:
      if (value == null) {
        unsetTemp();
      } else {
        setTemp((java.lang.Integer)value);
      }
      break;

    case BRIGHTNESS:
      if (value == null) {
        unsetBrightness();
      } else {
        setBrightness((java.lang.Integer)value);
      }
      break;

    case VOLUME:
      if (value == null) {
        unsetVolume();
      } else {
        setVolume((java.lang.Integer)value);
      }
      break;

    case TOPIC:
      if (value == null) {
        unsetTopic();
      } else {
        setTopic((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case LOCATION:
      return getLocation();

    case TIMESTAMP:
      return getTimestamp();

    case HUMIDITY:
      return getHumidity();

    case TEMP:
      return getTemp();

    case BRIGHTNESS:
      return getBrightness();

    case VOLUME:
      return getVolume();

    case TOPIC:
      return getTopic();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case LOCATION:
      return isSetLocation();
    case TIMESTAMP:
      return isSetTimestamp();
    case HUMIDITY:
      return isSetHumidity();
    case TEMP:
      return isSetTemp();
    case BRIGHTNESS:
      return isSetBrightness();
    case VOLUME:
      return isSetVolume();
    case TOPIC:
      return isSetTopic();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof SensorResource)
      return this.equals((SensorResource)that);
    return false;
  }

  public boolean equals(SensorResource that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_location = true && this.isSetLocation();
    boolean that_present_location = true && that.isSetLocation();
    if (this_present_location || that_present_location) {
      if (!(this_present_location && that_present_location))
        return false;
      if (!this.location.equals(that.location))
        return false;
    }

    boolean this_present_timestamp = true && this.isSetTimestamp();
    boolean that_present_timestamp = true && that.isSetTimestamp();
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (!this.timestamp.equals(that.timestamp))
        return false;
    }

    boolean this_present_humidity = true;
    boolean that_present_humidity = true;
    if (this_present_humidity || that_present_humidity) {
      if (!(this_present_humidity && that_present_humidity))
        return false;
      if (this.humidity != that.humidity)
        return false;
    }

    boolean this_present_temp = true;
    boolean that_present_temp = true;
    if (this_present_temp || that_present_temp) {
      if (!(this_present_temp && that_present_temp))
        return false;
      if (this.temp != that.temp)
        return false;
    }

    boolean this_present_brightness = true;
    boolean that_present_brightness = true;
    if (this_present_brightness || that_present_brightness) {
      if (!(this_present_brightness && that_present_brightness))
        return false;
      if (this.brightness != that.brightness)
        return false;
    }

    boolean this_present_volume = true;
    boolean that_present_volume = true;
    if (this_present_volume || that_present_volume) {
      if (!(this_present_volume && that_present_volume))
        return false;
      if (this.volume != that.volume)
        return false;
    }

    boolean this_present_topic = true && this.isSetTopic();
    boolean that_present_topic = true && that.isSetTopic();
    if (this_present_topic || that_present_topic) {
      if (!(this_present_topic && that_present_topic))
        return false;
      if (!this.topic.equals(that.topic))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetLocation()) ? 131071 : 524287);
    if (isSetLocation())
      hashCode = hashCode * 8191 + location.hashCode();

    hashCode = hashCode * 8191 + ((isSetTimestamp()) ? 131071 : 524287);
    if (isSetTimestamp())
      hashCode = hashCode * 8191 + timestamp.hashCode();

    hashCode = hashCode * 8191 + humidity;

    hashCode = hashCode * 8191 + temp;

    hashCode = hashCode * 8191 + brightness;

    hashCode = hashCode * 8191 + volume;

    hashCode = hashCode * 8191 + ((isSetTopic()) ? 131071 : 524287);
    if (isSetTopic())
      hashCode = hashCode * 8191 + topic.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(SensorResource other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetLocation()).compareTo(other.isSetLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.location, other.location);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHumidity()).compareTo(other.isSetHumidity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHumidity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.humidity, other.humidity);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTemp()).compareTo(other.isSetTemp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTemp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.temp, other.temp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetBrightness()).compareTo(other.isSetBrightness());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBrightness()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.brightness, other.brightness);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetVolume()).compareTo(other.isSetVolume());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVolume()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.volume, other.volume);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTopic()).compareTo(other.isSetTopic());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTopic()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topic, other.topic);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("SensorResource(");
    boolean first = true;

    sb.append("location:");
    if (this.location == null) {
      sb.append("null");
    } else {
      sb.append(this.location);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    if (this.timestamp == null) {
      sb.append("null");
    } else {
      sb.append(this.timestamp);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("humidity:");
    sb.append(this.humidity);
    first = false;
    if (!first) sb.append(", ");
    sb.append("temp:");
    sb.append(this.temp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("brightness:");
    sb.append(this.brightness);
    first = false;
    if (!first) sb.append(", ");
    sb.append("volume:");
    sb.append(this.volume);
    first = false;
    if (!first) sb.append(", ");
    sb.append("topic:");
    if (this.topic == null) {
      sb.append("null");
    } else {
      sb.append(this.topic);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SensorResourceStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SensorResourceStandardScheme getScheme() {
      return new SensorResourceStandardScheme();
    }
  }

  private static class SensorResourceStandardScheme extends org.apache.thrift.scheme.StandardScheme<SensorResource> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SensorResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LOCATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.location = iprot.readString();
              struct.setLocationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.timestamp = iprot.readString();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HUMIDITY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.humidity = iprot.readI32();
              struct.setHumidityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TEMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.temp = iprot.readI32();
              struct.setTempIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // BRIGHTNESS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.brightness = iprot.readI32();
              struct.setBrightnessIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // VOLUME
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.volume = iprot.readI32();
              struct.setVolumeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // TOPIC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.topic = iprot.readString();
              struct.setTopicIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SensorResource struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.location != null) {
        oprot.writeFieldBegin(LOCATION_FIELD_DESC);
        oprot.writeString(struct.location);
        oprot.writeFieldEnd();
      }
      if (struct.timestamp != null) {
        oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
        oprot.writeString(struct.timestamp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(HUMIDITY_FIELD_DESC);
      oprot.writeI32(struct.humidity);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TEMP_FIELD_DESC);
      oprot.writeI32(struct.temp);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BRIGHTNESS_FIELD_DESC);
      oprot.writeI32(struct.brightness);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VOLUME_FIELD_DESC);
      oprot.writeI32(struct.volume);
      oprot.writeFieldEnd();
      if (struct.topic != null) {
        oprot.writeFieldBegin(TOPIC_FIELD_DESC);
        oprot.writeString(struct.topic);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SensorResourceTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SensorResourceTupleScheme getScheme() {
      return new SensorResourceTupleScheme();
    }
  }

  private static class SensorResourceTupleScheme extends org.apache.thrift.scheme.TupleScheme<SensorResource> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SensorResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetLocation()) {
        optionals.set(0);
      }
      if (struct.isSetTimestamp()) {
        optionals.set(1);
      }
      if (struct.isSetHumidity()) {
        optionals.set(2);
      }
      if (struct.isSetTemp()) {
        optionals.set(3);
      }
      if (struct.isSetBrightness()) {
        optionals.set(4);
      }
      if (struct.isSetVolume()) {
        optionals.set(5);
      }
      if (struct.isSetTopic()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetLocation()) {
        oprot.writeString(struct.location);
      }
      if (struct.isSetTimestamp()) {
        oprot.writeString(struct.timestamp);
      }
      if (struct.isSetHumidity()) {
        oprot.writeI32(struct.humidity);
      }
      if (struct.isSetTemp()) {
        oprot.writeI32(struct.temp);
      }
      if (struct.isSetBrightness()) {
        oprot.writeI32(struct.brightness);
      }
      if (struct.isSetVolume()) {
        oprot.writeI32(struct.volume);
      }
      if (struct.isSetTopic()) {
        oprot.writeString(struct.topic);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SensorResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.location = iprot.readString();
        struct.setLocationIsSet(true);
      }
      if (incoming.get(1)) {
        struct.timestamp = iprot.readString();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(2)) {
        struct.humidity = iprot.readI32();
        struct.setHumidityIsSet(true);
      }
      if (incoming.get(3)) {
        struct.temp = iprot.readI32();
        struct.setTempIsSet(true);
      }
      if (incoming.get(4)) {
        struct.brightness = iprot.readI32();
        struct.setBrightnessIsSet(true);
      }
      if (incoming.get(5)) {
        struct.volume = iprot.readI32();
        struct.setVolumeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.topic = iprot.readString();
        struct.setTopicIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

