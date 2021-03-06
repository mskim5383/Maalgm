# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: session.proto

import sys
_b=sys.version_info[0]<3 and (lambda x:x) or (lambda x:x.encode('latin1'))
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='session.proto',
  package='',
  syntax='proto3',
  serialized_pb=_b('\n\rsession.proto\"2\n\x0cLoginRequest\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x10\n\x08password\x18\x02 \x01(\t\"2\n\rLoginResponse\x12\x0e\n\x06status\x18\x01 \x01(\t\x12\x11\n\tsessionId\x18\x02 \x01(\t\"\x1e\n\tSessionId\x12\x11\n\tsessionId\x18\x01 \x01(\t\" \n\x0eStatusResponse\x12\x0e\n\x06status\x18\x01 \x01(\t\"3\n\rSignUpRequest\x12\x10\n\x08username\x18\x01 \x01(\t\x12\x10\n\x08password\x18\x02 \x01(\t\"7\n\x13SessionDataResponse\x12\x0e\n\x06status\x18\x01 \x01(\t\x12\x10\n\x08username\x18\x02 \x01(\t\"1\n\x0f\x46\x65\x65\x64ListRequest\x12\x11\n\tsessionId\x18\x02 \x01(\t\x12\x0b\n\x03url\x18\x03 \x01(\t\"4\n\x10\x46\x65\x65\x64ListResponse\x12\x0e\n\x06status\x18\x01 \x01(\t\x12\x10\n\x08\x66\x65\x65\x64List\x18\x02 \x01(\t\"2\n\x0fUrlListResponse\x12\x0e\n\x06status\x18\x01 \x01(\t\x12\x0f\n\x07urlList\x18\x02 \x01(\t\"2\n\x10InsertUrlRequest\x12\x11\n\tsessionId\x18\x02 \x01(\t\x12\x0b\n\x03url\x18\x03 \x01(\t2\xd6\x02\n\x07session\x12(\n\x05login\x12\r.LoginRequest\x1a\x0e.LoginResponse\"\x00\x12\'\n\x06logout\x12\n.SessionId\x1a\x0f.StatusResponse\"\x00\x12+\n\x06signUp\x12\x0e.SignUpRequest\x1a\x0f.StatusResponse\"\x00\x12\x34\n\x0egetSessionData\x12\n.SessionId\x1a\x14.SessionDataResponse\"\x00\x12\x34\n\x0bgetFeedList\x12\x10.FeedListRequest\x1a\x11.FeedListResponse\"\x00\x12,\n\ngetUrlList\x12\n.SessionId\x1a\x10.UrlListResponse\"\x00\x12\x31\n\tinsertUrl\x12\x11.InsertUrlRequest\x1a\x0f.StatusResponse\"\x00\x42\x0f\n\rmaalgm_serverb\x06proto3')
)
_sym_db.RegisterFileDescriptor(DESCRIPTOR)




_LOGINREQUEST = _descriptor.Descriptor(
  name='LoginRequest',
  full_name='LoginRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='username', full_name='LoginRequest.username', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='password', full_name='LoginRequest.password', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=17,
  serialized_end=67,
)


_LOGINRESPONSE = _descriptor.Descriptor(
  name='LoginResponse',
  full_name='LoginResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='LoginResponse.status', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='sessionId', full_name='LoginResponse.sessionId', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=69,
  serialized_end=119,
)


_SESSIONID = _descriptor.Descriptor(
  name='SessionId',
  full_name='SessionId',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='sessionId', full_name='SessionId.sessionId', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=121,
  serialized_end=151,
)


_STATUSRESPONSE = _descriptor.Descriptor(
  name='StatusResponse',
  full_name='StatusResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='StatusResponse.status', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=153,
  serialized_end=185,
)


_SIGNUPREQUEST = _descriptor.Descriptor(
  name='SignUpRequest',
  full_name='SignUpRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='username', full_name='SignUpRequest.username', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='password', full_name='SignUpRequest.password', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=187,
  serialized_end=238,
)


_SESSIONDATARESPONSE = _descriptor.Descriptor(
  name='SessionDataResponse',
  full_name='SessionDataResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='SessionDataResponse.status', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='username', full_name='SessionDataResponse.username', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=240,
  serialized_end=295,
)


_FEEDLISTREQUEST = _descriptor.Descriptor(
  name='FeedListRequest',
  full_name='FeedListRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='sessionId', full_name='FeedListRequest.sessionId', index=0,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='url', full_name='FeedListRequest.url', index=1,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=297,
  serialized_end=346,
)


_FEEDLISTRESPONSE = _descriptor.Descriptor(
  name='FeedListResponse',
  full_name='FeedListResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='FeedListResponse.status', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='feedList', full_name='FeedListResponse.feedList', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=348,
  serialized_end=400,
)


_URLLISTRESPONSE = _descriptor.Descriptor(
  name='UrlListResponse',
  full_name='UrlListResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='status', full_name='UrlListResponse.status', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='urlList', full_name='UrlListResponse.urlList', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=402,
  serialized_end=452,
)


_INSERTURLREQUEST = _descriptor.Descriptor(
  name='InsertUrlRequest',
  full_name='InsertUrlRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='sessionId', full_name='InsertUrlRequest.sessionId', index=0,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    _descriptor.FieldDescriptor(
      name='url', full_name='InsertUrlRequest.url', index=1,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=_b("").decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=454,
  serialized_end=504,
)

DESCRIPTOR.message_types_by_name['LoginRequest'] = _LOGINREQUEST
DESCRIPTOR.message_types_by_name['LoginResponse'] = _LOGINRESPONSE
DESCRIPTOR.message_types_by_name['SessionId'] = _SESSIONID
DESCRIPTOR.message_types_by_name['StatusResponse'] = _STATUSRESPONSE
DESCRIPTOR.message_types_by_name['SignUpRequest'] = _SIGNUPREQUEST
DESCRIPTOR.message_types_by_name['SessionDataResponse'] = _SESSIONDATARESPONSE
DESCRIPTOR.message_types_by_name['FeedListRequest'] = _FEEDLISTREQUEST
DESCRIPTOR.message_types_by_name['FeedListResponse'] = _FEEDLISTRESPONSE
DESCRIPTOR.message_types_by_name['UrlListResponse'] = _URLLISTRESPONSE
DESCRIPTOR.message_types_by_name['InsertUrlRequest'] = _INSERTURLREQUEST

LoginRequest = _reflection.GeneratedProtocolMessageType('LoginRequest', (_message.Message,), dict(
  DESCRIPTOR = _LOGINREQUEST,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:LoginRequest)
  ))
_sym_db.RegisterMessage(LoginRequest)

LoginResponse = _reflection.GeneratedProtocolMessageType('LoginResponse', (_message.Message,), dict(
  DESCRIPTOR = _LOGINRESPONSE,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:LoginResponse)
  ))
_sym_db.RegisterMessage(LoginResponse)

SessionId = _reflection.GeneratedProtocolMessageType('SessionId', (_message.Message,), dict(
  DESCRIPTOR = _SESSIONID,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:SessionId)
  ))
_sym_db.RegisterMessage(SessionId)

StatusResponse = _reflection.GeneratedProtocolMessageType('StatusResponse', (_message.Message,), dict(
  DESCRIPTOR = _STATUSRESPONSE,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:StatusResponse)
  ))
_sym_db.RegisterMessage(StatusResponse)

SignUpRequest = _reflection.GeneratedProtocolMessageType('SignUpRequest', (_message.Message,), dict(
  DESCRIPTOR = _SIGNUPREQUEST,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:SignUpRequest)
  ))
_sym_db.RegisterMessage(SignUpRequest)

SessionDataResponse = _reflection.GeneratedProtocolMessageType('SessionDataResponse', (_message.Message,), dict(
  DESCRIPTOR = _SESSIONDATARESPONSE,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:SessionDataResponse)
  ))
_sym_db.RegisterMessage(SessionDataResponse)

FeedListRequest = _reflection.GeneratedProtocolMessageType('FeedListRequest', (_message.Message,), dict(
  DESCRIPTOR = _FEEDLISTREQUEST,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:FeedListRequest)
  ))
_sym_db.RegisterMessage(FeedListRequest)

FeedListResponse = _reflection.GeneratedProtocolMessageType('FeedListResponse', (_message.Message,), dict(
  DESCRIPTOR = _FEEDLISTRESPONSE,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:FeedListResponse)
  ))
_sym_db.RegisterMessage(FeedListResponse)

UrlListResponse = _reflection.GeneratedProtocolMessageType('UrlListResponse', (_message.Message,), dict(
  DESCRIPTOR = _URLLISTRESPONSE,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:UrlListResponse)
  ))
_sym_db.RegisterMessage(UrlListResponse)

InsertUrlRequest = _reflection.GeneratedProtocolMessageType('InsertUrlRequest', (_message.Message,), dict(
  DESCRIPTOR = _INSERTURLREQUEST,
  __module__ = 'session_pb2'
  # @@protoc_insertion_point(class_scope:InsertUrlRequest)
  ))
_sym_db.RegisterMessage(InsertUrlRequest)


DESCRIPTOR.has_options = True
DESCRIPTOR._options = _descriptor._ParseOptions(descriptor_pb2.FileOptions(), _b('\n\rmaalgm_server'))
import abc
from grpc.beta import implementations as beta_implementations
from grpc.framework.common import cardinality
from grpc.framework.interfaces.face import utilities as face_utilities

class BetasessionServicer(object):
  """<fill me in later!>"""
  __metaclass__ = abc.ABCMeta
  @abc.abstractmethod
  def login(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def logout(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def signUp(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def getSessionData(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def getFeedList(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def getUrlList(self, request, context):
    raise NotImplementedError()
  @abc.abstractmethod
  def insertUrl(self, request, context):
    raise NotImplementedError()

class BetasessionStub(object):
  """The interface to which stubs will conform."""
  __metaclass__ = abc.ABCMeta
  @abc.abstractmethod
  def login(self, request, timeout):
    raise NotImplementedError()
  login.future = None
  @abc.abstractmethod
  def logout(self, request, timeout):
    raise NotImplementedError()
  logout.future = None
  @abc.abstractmethod
  def signUp(self, request, timeout):
    raise NotImplementedError()
  signUp.future = None
  @abc.abstractmethod
  def getSessionData(self, request, timeout):
    raise NotImplementedError()
  getSessionData.future = None
  @abc.abstractmethod
  def getFeedList(self, request, timeout):
    raise NotImplementedError()
  getFeedList.future = None
  @abc.abstractmethod
  def getUrlList(self, request, timeout):
    raise NotImplementedError()
  getUrlList.future = None
  @abc.abstractmethod
  def insertUrl(self, request, timeout):
    raise NotImplementedError()
  insertUrl.future = None

def beta_create_session_server(servicer, pool=None, pool_size=None, default_timeout=None, maximum_timeout=None):
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  request_deserializers = {
    ('session', 'getFeedList'): session_pb2.FeedListRequest.FromString,
    ('session', 'getSessionData'): session_pb2.SessionId.FromString,
    ('session', 'getUrlList'): session_pb2.SessionId.FromString,
    ('session', 'insertUrl'): session_pb2.InsertUrlRequest.FromString,
    ('session', 'login'): session_pb2.LoginRequest.FromString,
    ('session', 'logout'): session_pb2.SessionId.FromString,
    ('session', 'signUp'): session_pb2.SignUpRequest.FromString,
  }
  response_serializers = {
    ('session', 'getFeedList'): session_pb2.FeedListResponse.SerializeToString,
    ('session', 'getSessionData'): session_pb2.SessionDataResponse.SerializeToString,
    ('session', 'getUrlList'): session_pb2.UrlListResponse.SerializeToString,
    ('session', 'insertUrl'): session_pb2.StatusResponse.SerializeToString,
    ('session', 'login'): session_pb2.LoginResponse.SerializeToString,
    ('session', 'logout'): session_pb2.StatusResponse.SerializeToString,
    ('session', 'signUp'): session_pb2.StatusResponse.SerializeToString,
  }
  method_implementations = {
    ('session', 'getFeedList'): face_utilities.unary_unary_inline(servicer.getFeedList),
    ('session', 'getSessionData'): face_utilities.unary_unary_inline(servicer.getSessionData),
    ('session', 'getUrlList'): face_utilities.unary_unary_inline(servicer.getUrlList),
    ('session', 'insertUrl'): face_utilities.unary_unary_inline(servicer.insertUrl),
    ('session', 'login'): face_utilities.unary_unary_inline(servicer.login),
    ('session', 'logout'): face_utilities.unary_unary_inline(servicer.logout),
    ('session', 'signUp'): face_utilities.unary_unary_inline(servicer.signUp),
  }
  server_options = beta_implementations.server_options(request_deserializers=request_deserializers, response_serializers=response_serializers, thread_pool=pool, thread_pool_size=pool_size, default_timeout=default_timeout, maximum_timeout=maximum_timeout)
  return beta_implementations.server(method_implementations, options=server_options)

def beta_create_session_stub(channel, host=None, metadata_transformer=None, pool=None, pool_size=None):
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  import session_pb2
  request_serializers = {
    ('session', 'getFeedList'): session_pb2.FeedListRequest.SerializeToString,
    ('session', 'getSessionData'): session_pb2.SessionId.SerializeToString,
    ('session', 'getUrlList'): session_pb2.SessionId.SerializeToString,
    ('session', 'insertUrl'): session_pb2.InsertUrlRequest.SerializeToString,
    ('session', 'login'): session_pb2.LoginRequest.SerializeToString,
    ('session', 'logout'): session_pb2.SessionId.SerializeToString,
    ('session', 'signUp'): session_pb2.SignUpRequest.SerializeToString,
  }
  response_deserializers = {
    ('session', 'getFeedList'): session_pb2.FeedListResponse.FromString,
    ('session', 'getSessionData'): session_pb2.SessionDataResponse.FromString,
    ('session', 'getUrlList'): session_pb2.UrlListResponse.FromString,
    ('session', 'insertUrl'): session_pb2.StatusResponse.FromString,
    ('session', 'login'): session_pb2.LoginResponse.FromString,
    ('session', 'logout'): session_pb2.StatusResponse.FromString,
    ('session', 'signUp'): session_pb2.StatusResponse.FromString,
  }
  cardinalities = {
    'getFeedList': cardinality.Cardinality.UNARY_UNARY,
    'getSessionData': cardinality.Cardinality.UNARY_UNARY,
    'getUrlList': cardinality.Cardinality.UNARY_UNARY,
    'insertUrl': cardinality.Cardinality.UNARY_UNARY,
    'login': cardinality.Cardinality.UNARY_UNARY,
    'logout': cardinality.Cardinality.UNARY_UNARY,
    'signUp': cardinality.Cardinality.UNARY_UNARY,
  }
  stub_options = beta_implementations.stub_options(host=host, metadata_transformer=metadata_transformer, request_serializers=request_serializers, response_deserializers=response_deserializers, thread_pool=pool, thread_pool_size=pool_size)
  return beta_implementations.dynamic_stub(channel, 'session', cardinalities, options=stub_options)
# @@protoc_insertion_point(module_scope)
